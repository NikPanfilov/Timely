package com.tsu.shared.schedule.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleDayDto(
	@Json(name = "date") val date: String,
	@Json(name = "timeslots") val timeSlots: List<TimeSlotDto>,
)
