package com.tsu.start.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.shared.GROUPS
import com.tsu.shared.TEACHERS
import com.tsu.shared.network.utils.CoroutineNetworkExceptionHandler
import com.tsu.start.domain.model.UserData
import com.tsu.start.domain.usecase.ClearUserDataUseCase
import com.tsu.start.domain.usecase.LoadUserDataUseCase
import com.tsu.start.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(
	private val router: StartRouter,
	loadUserData: LoadUserDataUseCase,
	private val logoutUseCase: LogoutUseCase,
	private val clearUserDataUseCase: ClearUserDataUseCase,
	private val isFromApp: Boolean
) : ViewModel() {

	private val _stateFlow = MutableStateFlow<StartState>(StartState.Initial)
	val stateFlow: Flow<StartState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? StartState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = StartSendState.Error(code))
	}

	private val data: UserData = loadUserData()

	init {
		if (isFromApp)
			x()
	}

	private fun x() {
		if (data.id != null && (data.type == GROUPS || data.type == TEACHERS))
			navigateToDailySchedule(data.type, data.id)
	}

	fun navigateToSignIn() {
		router.navigateToSignIn()
	}

	fun navigateToScheduleSelection(type: String) {
		router.navigateToScheduleSelection(type)
	}

	private fun navigateToDailySchedule(type: String, id: String) {
		router.navigateToDailySchedule(type, id)
	}

	fun logout() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = StartState.Content(StartSendState.Loading)
			logoutUseCase.invoke()
			clearUserDataUseCase()
			_stateFlow.value = StartState.Content(StartSendState.Success)
		}
	}

	fun isUserLogged() = (data.id != null || data.type != null)
}