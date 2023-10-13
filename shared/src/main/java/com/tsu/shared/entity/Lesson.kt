package com.tsu.shared.entity

import java.time.LocalDate

sealed class TimeSlot {

	abstract val type: String
	abstract val starts: String
	abstract val ends: String
	abstract val timeSlot: Int

	data class Lesson(
		val id: String,
		override val starts: String,
		override val ends: String,
		override val timeSlot: Int,
		val audience: Audience,
		override val type: String,
		val title: String,
		val groups: List<Group>,
		val professor: Professor,
	) : java.io.Serializable, TimeSlot()

	data class Break(
		override val type: String,
		override val starts: String,
		override val ends: String,
		override val timeSlot: Int,
		val date: LocalDate,
	) : TimeSlot()

	data class Booked(
		override val type: String,
		override val starts: String,
		override val ends: String,
		override val timeSlot: Int,
		val audience: Audience,
		val groups: List<Group>,
		val description: String,
	) : TimeSlot()
}
