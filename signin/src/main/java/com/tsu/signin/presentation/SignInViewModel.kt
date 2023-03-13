package com.tsu.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.signin.domain.entity.LoginData
import com.tsu.signin.domain.entity.TokenResponse
import com.tsu.signin.domain.usecase.LoginUseCase
import com.tsu.signin.domain.usecase.SaveTokenUseCase
import com.tsu.signin.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
	private val router: SignInRouter,
	private val loginUseCase: LoginUseCase,
	private val saveUserDataUseCase: SaveUserDataUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) :
	ViewModel() {

	val emailMutableFlow = MutableStateFlow<String?>("")
	val passwordMutableFlow = MutableStateFlow<String?>("")

	lateinit var tokenResponse: TokenResponse

	private val _stateFlow = MutableStateFlow<SignInState>(SignInState.Initial)
	val stateFlow: Flow<SignInState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? SignInState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = SignInSendState.Error(code))
	}

	 init {
		_stateFlow.value = SignInState.Content(SignInSendState.Input)
	}

	fun signIn() {
		val contentState = _stateFlow.value as? SignInState.Content ?: return
		if (contentState.sendState is SignInSendState.Loading) return

		if (!isDataValid()) return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = contentState.copy(sendState = SignInSendState.Loading)
			tokenResponse = loginUseCase(buildLoginData())
			_stateFlow.value = contentState.copy(sendState = SignInSendState.Success)
		}
	}

	private fun isDataValid() = !emailMutableFlow.value.isNullOrEmpty() && !passwordMutableFlow.value.isNullOrEmpty()

	private fun buildLoginData() = LoginData(
		email = emailMutableFlow.value ?: error("Email can't be null!"), password = passwordMutableFlow.value ?: error("Password can't be null!")
	)

	fun navigateToDailySchedule(type: String, id: String) {
		saveUserDataUseCase(type, id)
		router.navigateToDailySchedule(type = type, id = id)
	}

	fun saveToken() {
		tokenResponse.token?.let { saveTokenUseCase(it) }
	}

	fun navigateToStart(type: String) {
		saveUserDataUseCase(type, "")
		router.navigateToStart()
	}
}