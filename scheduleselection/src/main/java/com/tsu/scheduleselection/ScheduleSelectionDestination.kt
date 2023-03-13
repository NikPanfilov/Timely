package com.tsu.scheduleselection

import com.tsu.scheduleselection.ui.ScheduleSelectionFragment
import com.tsu.shared.navigation.FragmentDestination

class ScheduleSelectionDestination(
	private val scheduleType: String,
) : FragmentDestination {

	override fun createInstance() = ScheduleSelectionFragment.newInstance(scheduleType)
}