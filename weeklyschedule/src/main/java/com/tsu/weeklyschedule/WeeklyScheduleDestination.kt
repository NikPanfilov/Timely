package com.tsu.weeklyschedule

import com.tsu.shared.navigation.FragmentDestination
import com.tsu.weeklyschedule.ui.WeeklyScheduleFragment
import java.time.LocalDate

class WeeklyScheduleDestination(
	private val id: String,
	private val scheduleType: String,
	private val date: LocalDate
) : FragmentDestination {

	override fun createInstance() = WeeklyScheduleFragment.newInstance(id, scheduleType,date)
}