package com.tsu.dailyschedule.presentation

sealed class DailyScheduleState {

	object Initial : DailyScheduleState()

	data class Content(val sendState: DailyScheduleSendState) : DailyScheduleState()
}

sealed class DailyScheduleSendState {

	object Input : DailyScheduleSendState()

	object Loading : DailyScheduleSendState()

	object Success : DailyScheduleSendState()

	data class Error(val errorCode: Int) : DailyScheduleSendState()
}