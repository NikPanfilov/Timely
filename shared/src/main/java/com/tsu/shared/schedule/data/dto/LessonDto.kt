package com.tsu.shared.schedule.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupDto(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String,
)

@JsonClass(generateAdapter = true)
data class ProfessorDto(
	@Json(name = "id") val id: String,
	@Json(name = "fullName") val fullName: String,
	@Json(name = "shortName") val shortName: String,
)

@JsonClass(generateAdapter = true)
data class AudienceDto(
	@Json(name = "id") val id: String?,
	@Json(name = "name") val name: String,
)

@JsonClass(generateAdapter = true)
data class SubjectDto(
	@Json(name = "id") val id: String?,
	@Json(name = "name") val name: String?,
)



