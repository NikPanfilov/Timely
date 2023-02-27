package com.tsu.timely.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.tsu.shared.navigation.FragmentDestination
import com.tsu.shared.navigation.GlobalRouter

class GlobalRouterImpl(
	private val router: Router
) : GlobalRouter {

	override fun open(destination: FragmentDestination) {
		router.navigateTo(CreateInstanceFragmentScreen(destination))
	}

	override fun replace(fragmentDestination: FragmentDestination) {
		router.replaceScreen(
			CreateInstanceFragmentScreen(fragmentDestination)
		)
	}

	override fun newRoot(fragmentDestination: FragmentDestination) {
		router.newRootScreen(
			CreateInstanceFragmentScreen(fragmentDestination)
		)
	}

	override fun popToRoot() {
		router.backTo(null)
	}

	override fun exit() {
		router.exit()
	}

	override fun finish() {
		router.finishChain()
	}

	override fun popTo(fragmentDestinationClass: Class<out FragmentDestination>) {
		router.backTo(
			BackToFragmentScreen(fragmentDestinationClass.name)
		)
	}
}

private class CreateInstanceFragmentScreen(
	private val fragmentDestination: FragmentDestination,
) : FragmentScreen {

	override val screenKey: String = fragmentDestination::class.java.name

	override fun createFragment(factory: FragmentFactory): Fragment =
		fragmentDestination.createInstance()
}

private class BackToFragmentScreen(override val screenKey: String) : Screen