package com.tsu.scheduleselection

import androidx.fragment.app.Fragment
import com.tsu.scheduleselection.ui.ScheduleSelectionFragment
import com.tsu.shared.ScheduleType
import com.tsu.shared.navigation.FragmentDestination

class ScheduleSelectionDestination(
	private val scheduleType: ScheduleType,
) : FragmentDestination {

	override fun createInstance(): Fragment = ScheduleSelectionFragment.newInstance(scheduleType)
}