package com.tsu.dailyschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.dailyschedule.domain.entity.DailyItem
import com.tsu.dailyschedule.domain.entity.EmptyLessonItem
import com.tsu.dailyschedule.domain.entity.LessonItem
import com.tsu.dailyschedule.domain.usecase.GetDailyScheduleUseCase
import com.tsu.shared.entity.Lesson
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.shared.time.intervals.domain.entity.TimeInterval
import com.tsu.shared.time.intervals.domain.usecase.GetTimeIntervalsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

class DailyScheduleViewModel(
	private val id: String,
	private val scheduleType: String,
	private val router: DailyScheduleRouter,
	private val getDailyScheduleUseCase: GetDailyScheduleUseCase,
	private val getTimeIntervalsUseCase: GetTimeIntervalsUseCase
) : ViewModel() {

	var date: LocalDate = LocalDate.now()

	val schedule: MutableStateFlow<List<DailyItem>> = MutableStateFlow(listOf())

	var timeIntervals: List<TimeInterval> = listOf()

	private val _stateFlow = MutableStateFlow<DailyScheduleState>(DailyScheduleState.Initial)
	val stateFlow: Flow<DailyScheduleState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? DailyScheduleState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = DailyScheduleSendState.Error(code))
	}

	fun init() {
		_stateFlow.value = DailyScheduleState.Content(DailyScheduleSendState.Input)
		getSchedule()
	}

	fun getSchedule() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = DailyScheduleState.Content(DailyScheduleSendState.Loading)

			timeIntervals = getTimeIntervalsUseCase().sortedBy { it.startTime }
			val scheduleList = getDailyScheduleUseCase(type = scheduleType.dropLast(1), id = id, date = date).sortedBy { it.timeInterval.startTime }

			val resultList: MutableList<DailyItem> = mutableListOf()
			for (interval in timeIntervals) {
				resultList.add(
					scheduleList.find { it.timeInterval.startTime == interval.startTime }?.toItem() ?: EmptyLessonItem(
						startTime = interval.startTime, endTime = interval.endTime
					)
				)
			}
			schedule.value = resultList.toList()

			_stateFlow.value = DailyScheduleState.Content(DailyScheduleSendState.Success)
		}
	}

	fun changeDate(day: DayOfWeek) {
		date = date.with(day)
		getSchedule()
	}

	fun navigateToWeeklySchedule() {
		router.navigateToWeeklySchedule(id, scheduleType, date)
	}

	fun navigateToStart() {
		router.navigateToStart()
	}

	fun navigateToDetails(lesson: Lesson) {
		router.navigateToDetails(LessonHolder(lesson))
	}

	fun hasLessons(): Boolean {
		schedule.value.forEach {
			if (it is LessonItem) return true
		}
		return false
	}

	private fun Lesson.toItem() = LessonItem(this)
}