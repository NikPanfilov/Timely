package com.tsu.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.network.token.domain.usecase.SaveTokenUseCase
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.signup.domain.entity.TokenResponse
import com.tsu.signup.domain.entity.UserRegisterData
import com.tsu.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
	private val router: SignUpRouter,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val signUpUseCase: SignUpUseCase
) : ViewModel() {

	val emailMutableFlow = MutableStateFlow<String?>("")
	val passwordMutableFlow = MutableStateFlow<String?>("")
	val fullNameMutableFlow = MutableStateFlow<String?>("")

	lateinit var tokenResponse: TokenResponse

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

	fun signUp() {
		val contentState = _stateFlow.value as? SignUpState.Content ?: return
		if (contentState.sendState is SignUpSendState.Loading) return

		if (!isDataValid())
			return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = contentState.copy(sendState = SignUpSendState.Loading)
			tokenResponse = signUpUseCase(
				UserRegisterData(
					email = emailMutableFlow.value!!,
					password = passwordMutableFlow.value!!,
					fullName = fullNameMutableFlow.value!!
				)
			)
			_stateFlow.value = contentState.copy(sendState = SignUpSendState.Success)
		}
	}

	private fun isDataValid(): Boolean {
		if (emailMutableFlow.value == null || passwordMutableFlow.value == null || fullNameMutableFlow.value == null)
			return false
		return true
	}

	fun saveToken() {
		saveTokenUseCase(tokenResponse.token!!)
	}

	fun navigateToStart() {
		router.navigateToStart()
	}
}