package com.tsu.dailyschedule.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.date.toFormattedString
import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.TimeSlot
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.shared.schedule.domain.entity.Booking
import com.tsu.shared.schedule.domain.usecase.BookAudienceUseCase
import com.tsu.shared.schedule.domain.usecase.GetAudiencesUseCase
import com.tsu.shared.schedule.domain.usecase.GetDailyScheduleUseCase
import com.tsu.shared.schedule.domain.usecase.GetGroupsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate

class DailyScheduleViewModel(
	private val id: String,
	private val scheduleType: String,
	private val router: DailyScheduleRouter,
	private val getDailyScheduleUseCase: GetDailyScheduleUseCase,
	private val bookAudienceUseCase: BookAudienceUseCase,
	private val getAudiencesUseCase: GetAudiencesUseCase,
	private val getGroupsUseCase: GetGroupsUseCase,
) : ViewModel() {

	var date: LocalDate = LocalDate.now()

	val schedule: MutableStateFlow<List<TimeSlot>> = MutableStateFlow(listOf())

	val audiences = MutableStateFlow<List<Audience>>(emptyList())
	val groups = MutableStateFlow<List<Group>>(emptyList())

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
			schedule.value = getDailyScheduleUseCase(type = scheduleType, itemId = id, date = date).sortedBy { it.starts }
			groups.value = getGroupsUseCase()
			_stateFlow.value = DailyScheduleState.Content(DailyScheduleSendState.Success)
		}
	}

	fun getFreeAudiences(timeSlot: Int): List<Audience> {
		audiences.value = runBlocking { getAudiencesUseCase(date, timeSlot) }
		return audiences.value
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

	fun navigateToDetails(lesson: TimeSlot.Lesson) {
		router.navigateToDetails(LessonHolder(lesson))
	}

	fun bookAudience(audienceName: String, groupsId: List<String>, timeSlot: Int, description: String) {
		viewModelScope.launch(sendErrorHandler) {
			Log.i("debug", audienceName)
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
}