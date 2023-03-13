package com.tsu.timely.presentation

import androidx.lifecycle.ViewModel

class MainViewModel(
	private val router: MainActivityRouter
) : ViewModel() {

	fun openStartScreen() {
		router.navigateToStartScreen(true)
	}
}