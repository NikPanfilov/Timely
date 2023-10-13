package com.tsu.shared.schedule.domain.entity

data class Booking(
	val date: String,
	val timeSlot: Int,
	val audienceId: String,
	val description: String,
	val groupsId: List<String>?,
)
