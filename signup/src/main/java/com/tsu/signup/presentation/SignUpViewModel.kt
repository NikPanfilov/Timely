package com.tsu.signup.presentation

import androidx.lifecycle.ViewModel
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel(
	private val router: SignUpRouter
) : ViewModel() {

	private val _stateFlow = MutableStateFlow<SignUpState>(SignUpState.Initial)
	val stateFlow: Flow<SignUpState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? SignUpState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = SignUpSendState.Error(code))
	}

	init {
		_stateFlow.value = SignUpState.Content(SignUpSendState.Input)
	}

	fun signUp(){

	}
}