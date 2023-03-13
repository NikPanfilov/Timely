package com.tsu.signin.presentation

interface SignInRouter {

	fun navigateToDailySchedule(id: String, type: String)

	fun navigateToStart()
}