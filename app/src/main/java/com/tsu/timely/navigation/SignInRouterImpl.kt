package com.tsu.timely.navigation

import com.tsu.dailyschedule.DailyScheduleDestination
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.signin.presentation.SignInRouter
import com.tsu.start.StartDestination

class SignInRouterImpl(
	private val router: GlobalRouter
) : SignInRouter {

	override fun navigateToDailySchedule(id: String, type: String) {
		router.open(DailyScheduleDestination(id, type))
	}

	override fun navigateToStart() {
		router.open(StartDestination(false))
	}
}