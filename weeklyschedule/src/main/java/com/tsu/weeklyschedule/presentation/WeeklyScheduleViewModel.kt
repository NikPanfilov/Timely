package com.tsu.weeklyschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.entity.Lesson
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.shared.time.intervals.domain.entity.TimeInterval
import com.tsu.shared.time.intervals.domain.usecase.GetTimeIntervalsUseCase
import com.tsu.weeklyschedule.domain.usecase.GetWeeklyScheduleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class WeeklyScheduleViewModel(
	private val id: String,
	private val scheduleType: String,
	private val currentDate: LocalDate,
	private val router: WeeklyScheduleRouter,
	private val getWeeklyScheduleUseCase: GetWeeklyScheduleUseCase,
	private val getTimeIntervalsUseCase: GetTimeIntervalsUseCase
) : ViewModel() {

	var date = currentDate

	lateinit var intervals: List<TimeInterval>

	private val _schedule = MutableStateFlow<List<Lesson>>(listOf())
	val schedule: Flow<List<Lesson>>
		get() = _schedule.asStateFlow()

	private val _stateFlow = MutableStateFlow<WeeklyScheduleState>(WeeklyScheduleState.Initial)
	val stateFlow: Flow<WeeklyScheduleState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? WeeklyScheduleState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = WeeklyScheduleSendState.Error(code))
	}

	fun init() {
		_stateFlow.value = WeeklyScheduleState.Content(WeeklyScheduleSendState.Input)
		getSchedule()
	}

	fun getSchedule() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = WeeklyScheduleState.Content(WeeklyScheduleSendState.Loading)
			intervals = getTimeIntervalsUseCase().sortedBy { it.startTime }
			_schedule.value = getWeeklyScheduleUseCase(type = scheduleType.dropLast(1), id = id, date = date)
			_stateFlow.value = WeeklyScheduleState.Content(WeeklyScheduleSendState.Success)
		}
	}

	fun renderSchedule(): List<List<Lesson?>> {
		val scheduleTable = mutableListOf<List<Lesson?>>()
		for (interval in intervals) {
			scheduleTable.add(createRow(_schedule.value.filter { it.timeInterval.startTime == interval.startTime }.sortedBy { it.date }))
		}

		return scheduleTable
	}

	private fun createRow(row: List<Lesson>): List<Lesson?> {
		val monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

		val resultSchedule = mutableListOf<Lesson?>()
		for (i in 0..6) {
			val currentDate = monday.plusDays(i.toLong())
			resultSchedule.add(row.find { it.date == currentDate })
		}

		return resultSchedule
	}

	fun navigateToWeeklySchedule() {
		router.navigateToDailySchedule(id, scheduleType)
	}

	fun navigateToStart() {
		router.navigateToStart()
	}

	fun navigateToDetails(lesson: Lesson) {
		router.navigateToDetails(LessonHolder(lesson))
	}
}