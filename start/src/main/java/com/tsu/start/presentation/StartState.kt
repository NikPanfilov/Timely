package com.tsu.start.presentation

sealed class StartState {

	object Initial : StartState()

	data class Content(val sendState: StartSendState) : StartState()
}

sealed class StartSendState {

	object Input : StartSendState()

	object Loading : StartSendState()

	object Success : StartSendState()

	data class Error(val errorCode: Int) : StartSendState()
}