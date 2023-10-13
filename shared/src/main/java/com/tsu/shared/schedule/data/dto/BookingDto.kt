package com.tsu.shared.schedule.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingDto(
	@Json(name = "date") val date: String,
	@Json(name = "timeslot") val timeSlot: Int,
	@Json(name = "audienceId") val audienceId: String,
	@Json(name = "description") val description: String,
	@Json(name = "groupsId") val groupsId: List<String>?,
)
