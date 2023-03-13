package com.tsu.dailyschedule

import androidx.fragment.app.Fragment
import com.tsu.dailyschedule.ui.DailyScheduleFragment
import com.tsu.shared.navigation.FragmentDestination

class DailyScheduleDestination(
	private val id: String,
	private val scheduleType: String
) : FragmentDestination {

	override fun createInstance(): Fragment = DailyScheduleFragment.newInstance(id, scheduleType)
}