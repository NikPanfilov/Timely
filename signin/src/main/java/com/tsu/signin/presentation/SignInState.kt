package com.tsu.signin.presentation

sealed class SignInState {

	object Initial : SignInState()

	data class Content(val sendState: SignInSendState) : SignInState()
}

sealed class SignInSendState {

	object Input : SignInSendState()

	object Loading : SignInSendState()

	object Success : SignInSendState()

	data class Error(val errorCode: Int) : SignInSendState()
}