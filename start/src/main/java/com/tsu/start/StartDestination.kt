package com.tsu.start

import androidx.fragment.app.Fragment
import com.tsu.shared.navigation.FragmentDestination
import com.tsu.start.ui.StartFragment

object StartDestination : FragmentDestination {

	override fun createInstance(): Fragment = StartFragment()
}