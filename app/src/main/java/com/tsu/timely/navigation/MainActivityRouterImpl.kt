package com.tsu.timely.navigation

import com.tsu.shared.navigation.GlobalRouter
import com.tsu.start.StartDestination
import com.tsu.timely.presentation.MainActivityRouter

class MainActivityRouterImpl(
	private val router: GlobalRouter
) : MainActivityRouter {

	override fun navigateToStartScreen(isFromApp:Boolean) {
		router.open(StartDestination(isFromApp))
	}
}