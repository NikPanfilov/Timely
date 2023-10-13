package com.tsu.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponseDto(
	@Json(name = "accessToken") val token: String?,
	@Json(name = "email") val email: String?,
	@Json(name = "role") val role: List<String?>,
	@Json(name = "group") val group: String,
	@Json(name = "teacher") val teacher: String
)
