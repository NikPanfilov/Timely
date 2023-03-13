package com.tsu.details.presentation

import androidx.lifecycle.ViewModel
import com.tsu.shared.navigation.holder.LessonHolder

class DetailsViewModel(private val router: DetailsRouter, lessonHolder: LessonHolder) : ViewModel() {

	val lesson = lessonHolder.lesson

	init {

	}

	fun navigateToDailySchedule(id: String, scheduleType: String) {
		router.navigateToDailySchedule(id, scheduleType)
	}
}