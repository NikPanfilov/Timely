package com.tsu.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponseDto(
	@Json(name = "token") val token: String?,
	@Json(name = "email") val email: String?,
	@Json(name = "role") val role: List<String?>,
	@Json(name = "group") val group: GroupDto?,
	@Json(name = "teacher") val teacher: TeacherDto?
)

@JsonClass(generateAdapter = true)
data class GroupDto(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?,
)

@JsonClass(generateAdapter = true)
data class TeacherDto(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?,
)
