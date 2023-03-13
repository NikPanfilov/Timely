package com.tsu.timely.navigation

import com.tsu.shared.navigation.GlobalRouter
import com.tsu.signup.presentation.SignUpRouter
import com.tsu.start.StartDestination

class SignUpRouterImpl(
	private val router: GlobalRouter
) : SignUpRouter {

	override fun navigateToStart() {
		router.open(StartDestination(false))
	}
}