package com.tsu.timely.navigation

import com.tsu.dailyschedule.DailyScheduleDestination
import com.tsu.scheduleselection.ScheduleSelectionDestination
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.signin.SignInDestination
import com.tsu.signup.SignUpDestination
import com.tsu.start.presentation.StartRouter

class StartRouterImpl(
	private val router: GlobalRouter
) : StartRouter {

	override fun navigateToSignIn() {
		router.open(SignInDestination())
	}

	override fun navigateToSignUp() {
		router.open(SignUpDestination())
	}

	override fun navigateToScheduleSelection(type: String) {
		router.open(ScheduleSelectionDestination(scheduleType = type))
	}

	override fun navigateToDailySchedule(type: String, id: String) {
		router.open(DailyScheduleDestination(scheduleType = type, id = id))
	}
}