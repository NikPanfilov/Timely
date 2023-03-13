package com.tsu.weeklyschedule.presentation

sealed class WeeklyScheduleState {

	object Initial : WeeklyScheduleState()

	data class Content(val sendState: WeeklyScheduleSendState) : WeeklyScheduleState()
}

sealed class WeeklyScheduleSendState {

	object Input : WeeklyScheduleSendState()

	object Loading : WeeklyScheduleSendState()

	object Success : WeeklyScheduleSendState()

	data class Error(val errorCode: Int) : WeeklyScheduleSendState()
}