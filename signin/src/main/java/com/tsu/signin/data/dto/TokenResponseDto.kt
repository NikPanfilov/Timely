package com.tsu.signin.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponseDto(
	@Json(name = "token") val token: String?,
	@Json(name = "email") val email: String?,
	@Json(name = "role") val role: List<String?>
)