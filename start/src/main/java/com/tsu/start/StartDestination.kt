package com.tsu.start

import com.tsu.shared.navigation.FragmentDestination
import com.tsu.start.ui.StartFragment

class StartDestination(
	private val isFromApp: Boolean,
) : FragmentDestination {

	override fun createInstance() = StartFragment.newInstance(isFromApp)
}