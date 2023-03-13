package com.tsu.dailyschedule.domain.entity

import com.tsu.dailyschedule.R
import com.tsu.shared.entity.Lesson

data class LessonItem(
	val lesson: Lesson
) : DailyItem {

	override val viewType: Int
		get() = R.layout.lesson_item
}
