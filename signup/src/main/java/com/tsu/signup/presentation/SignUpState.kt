package com.tsu.signup.presentation

sealed class SignUpState {

	object Initial : SignUpState()

	data class Content(val sendState: SignUpSendState) : SignUpState()
}

sealed class SignUpSendState {

	object Input : SignUpSendState()

	object Loading : SignUpSendState()

	object Success : SignUpSendState()

	data class Error(val errorCode: Int) : SignUpSendState()
}