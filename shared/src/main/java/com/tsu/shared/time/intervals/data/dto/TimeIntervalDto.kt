package com.tsu.shared.time.intervals.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeIntervalDto(
	@Json(name = "id") val id: String?,
	@Json(name = "startTime") val startTime: String,
	@Json(name = "endTime") val endTime: String
)

