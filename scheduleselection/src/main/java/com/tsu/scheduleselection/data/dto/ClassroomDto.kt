package com.tsu.scheduleselection.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassroomDto(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String
)
