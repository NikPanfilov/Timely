package com.tsu.weeklyschedule.presentation

import com.tsu.shared.navigation.holder.LessonHolder

interface WeeklyScheduleRouter {

	fun navigateToDailySchedule(id: String, type: String)

	fun navigateToStart()

	fun navigateToDetails(lessonHolder: LessonHolder)
}