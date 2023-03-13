package com.tsu.start.presentation

interface StartRouter {

	fun navigateToSignIn()
	fun navigateToScheduleSelection(type: String)
	fun navigateToDailySchedule(type: String, id: String)
}