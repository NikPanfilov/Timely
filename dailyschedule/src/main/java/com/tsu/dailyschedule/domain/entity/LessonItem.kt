package com.tsu.dailyschedule.domain.entity

import com.tsu.dailyschedule.R
import com.tsu.shared.entity.TimeSlot

data class LessonItem(
	val lesson: TimeSlot.Lesson
) : DailyItem {

	override val viewType: Int
		get() = R.layout.lesson_item
}

data class BreakItem(
	val breakItem: TimeSlot.Break
) : DailyItem {

	override val viewType: Int
		get() = R.layout.break_item
}

data class BookedItem(
	val booked: TimeSlot.Booked
) : DailyItem {

	override val viewType: Int
		get() = R.layout.book_item
}
