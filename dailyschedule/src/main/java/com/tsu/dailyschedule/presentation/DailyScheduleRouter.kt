package com.tsu.dailyschedule.presentation

import com.tsu.shared.navigation.holder.LessonHolder
import java.time.LocalDate

interface DailyScheduleRouter {

	fun navigateToWeeklySchedule(id: String, type: String, date: LocalDate)

	fun navigateToStart()

	fun navigateToDetails(lessonHolder: LessonHolder)
}