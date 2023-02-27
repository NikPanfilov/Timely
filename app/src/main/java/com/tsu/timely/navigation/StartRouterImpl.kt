package com.tsu.timely.navigation

import com.tsu.scheduleselection.ScheduleSelectionDestination
import com.tsu.shared.ScheduleType
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.signin.SignInDestination
import com.tsu.start.presentation.StartRouter

class StartRouterImpl(
	private val router: GlobalRouter
) : StartRouter {

	override fun navigateToSignIn() {
		router.open(SignInDestination)
	}

	override fun navigateToScheduleSelection(type: ScheduleType) {
		router.open(ScheduleSelectionDestination(scheduleType = type))
	}

	override fun navigateToSchedule() {
		//router.open()
	}
}