package com.tsu.signup.presentation

interface SignUpRouter {

	fun navigateToDailySchedule(id: String, type: String)

	fun navigateToStart()
}