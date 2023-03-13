package com.tsu.timely.navigation

import com.tsu.dailyschedule.DailyScheduleDestination
import com.tsu.details.DetailsDestination
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.start.StartDestination
import com.tsu.weeklyschedule.presentation.WeeklyScheduleRouter

class WeeklyScheduleRouterImpl(
	private val router: GlobalRouter
) : WeeklyScheduleRouter {

	override fun navigateToDailySchedule(id: String, type: String) {
		router.open(DailyScheduleDestination(id = id, scheduleType = type))
	}

	override fun navigateToStart() {
		router.open(StartDestination(false))
	}

	override fun navigateToDetails(lessonHolder: LessonHolder) {
		router.open(DetailsDestination(lessonHolder))
	}
}