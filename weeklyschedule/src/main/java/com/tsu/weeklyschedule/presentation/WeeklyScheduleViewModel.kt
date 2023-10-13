package com.tsu.weeklyschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.date.toFormattedString
import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.TimeSlot
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.shared.schedule.domain.entity.Booking
import com.tsu.shared.schedule.domain.entity.ScheduleDay
import com.tsu.shared.schedule.domain.usecase.BookAudienceUseCase
import com.tsu.shared.schedule.domain.usecase.GetAudiencesUseCase
import com.tsu.shared.schedule.domain.usecase.GetGroupsUseCase
import com.tsu.shared.schedule.domain.usecase.GetWeeklyScheduleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class WeeklyScheduleViewModel(
	private val id: String,
	private val scheduleType: String,
	private val currentDate: LocalDate,
	private val router: WeeklyScheduleRouter,
	private val getWeeklyScheduleUseCase: GetWeeklyScheduleUseCase,
	private val bookAudienceUseCase: BookAudienceUseCase,
	private val getAudiencesUseCase: GetAudiencesUseCase,
	private val getGroupsUseCase: GetGroupsUseCase,
) : ViewModel() {

	var date = currentDate

	private val _schedule = MutableStateFlow<List<ScheduleDay>>(listOf())

	val audiences = MutableStateFlow<List<Audience>>(emptyList())
	val groups = MutableStateFlow<List<Group>>(emptyList())

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
			_schedule.value = getWeeklyScheduleUseCase(type = scheduleType, itemId = id, date = date)
			groups.value = getGroupsUseCase()
			_stateFlow.value = WeeklyScheduleState.Content(WeeklyScheduleSendState.Success)
		}
	}

	fun renderSchedule(): List<List<TimeSlot>> {
		val scheduleTable = mutableListOf<List<TimeSlot>>()
		val sortedSchedule = _schedule.value.sortedBy { it.date }
		for (slot in 1..7) {
			val day = mutableListOf<TimeSlot>()
			sortedSchedule.forEach { day.add(it.timeSlots.first { schedule -> schedule.timeSlot == slot }) }
			scheduleTable.add(day)
		}

		return scheduleTable
	}

	fun getFreeAudiences(date: LocalDate, timeSlot: Int): List<Audience> {
		audiences.value = runBlocking { getAudiencesUseCase(date, timeSlot) }
		return audiences.value
	}

	fun bookAudience(audienceName: String, groupsId: List<String>, timeSlot: Int, description: String) {
		viewModelScope.launch(sendErrorHandler) {
			val audienceId = audiences.value.find { it.name == audienceName }?.id ?: return@launch
			bookAudienceUseCase(
				Booking(
					date = date.toFormattedString(),
					description = description,
					groupsId = groupsId,
					audienceId = audienceId,
					timeSlot = timeSlot,
				)
			)
			getSchedule()
		}
	}

	fun navigateToWeeklySchedule() {
		router.navigateToDailySchedule(id, scheduleType)
	}

	fun navigateToStart() {
		router.navigateToStart()
	}

	fun navigateToDetails(lesson: TimeSlot.Lesson) {
		router.navigateToDetails(LessonHolder(lesson))
	}
}