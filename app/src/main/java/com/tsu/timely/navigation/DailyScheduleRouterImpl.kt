package com.tsu.timely.navigation

import com.tsu.dailyschedule.presentation.DailyScheduleRouter
import com.tsu.details.DetailsDestination
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.shared.navigation.holder.LessonHolder
import com.tsu.start.StartDestination
import com.tsu.weeklyschedule.WeeklyScheduleDestination
import java.time.LocalDate

class DailyScheduleRouterImpl(
	private val router: GlobalRouter
) : DailyScheduleRouter {

	override fun navigateToWeeklySchedule(id: String, type: String, date: LocalDate) {
		router.open(WeeklyScheduleDestination(id = id, scheduleType = type, date = date))
	}

	override fun navigateToStart() {
		router.open(StartDestination(false))
	}

	override fun navigateToDetails(lessonHolder: LessonHolder) {
		router.open(DetailsDestination(lessonHolder))
	}
}