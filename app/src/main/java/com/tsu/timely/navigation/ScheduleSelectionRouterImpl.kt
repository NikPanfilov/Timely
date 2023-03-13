package com.tsu.timely.navigation

import com.tsu.dailyschedule.DailyScheduleDestination
import com.tsu.scheduleselection.presentation.ScheduleSelectionRouter
import com.tsu.shared.navigation.GlobalRouter

class ScheduleSelectionRouterImpl(
	private val router: GlobalRouter
) : ScheduleSelectionRouter {

	override fun navigateToDailySchedule(id: String,type: String) {
		router.open(DailyScheduleDestination(id,type))
	}
}