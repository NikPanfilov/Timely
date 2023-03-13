package com.tsu.dailyschedule.domain.entity

import com.tsu.dailyschedule.R

data class EmptyLessonItem(
	val startTime: String,
	val endTime: String
):DailyItem{
	override val viewType: Int
		get() = R.layout.empty_lesson_item
}
