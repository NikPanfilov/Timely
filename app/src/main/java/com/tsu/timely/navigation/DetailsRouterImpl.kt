package com.tsu.timely.navigation

import com.tsu.dailyschedule.DailyScheduleDestination
import com.tsu.details.presentation.DetailsRouter
import com.tsu.shared.navigation.GlobalRouter

class DetailsRouterImpl(
	private val router: GlobalRouter
) : DetailsRouter {

	override fun navigateToDailySchedule(id: String, type: String) {
		router.open(DailyScheduleDestination(id = id, scheduleType = type))
	}
}