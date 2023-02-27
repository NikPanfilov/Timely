package com.tsu.start.presentation

import androidx.lifecycle.ViewModel
import com.tsu.shared.ScheduleType
import com.tsu.start.domain.usecase.LoadUserDataUseCase

class StartViewModel(
	private val router: StartRouter, private val loadUserData: LoadUserDataUseCase
) : ViewModel() {

	fun navigateToSignIn() {
		router.navigateToSignIn()
	}

	fun navigateToScheduleSelection(type: ScheduleType) {
		router.navigateToScheduleSelection(type)
	}
}