package com.tsu.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRegisterDataDto(
	@Json(name = "username") val fullName: String,
	@Json(name = "password") val password: String,
	@Json(name = "email") val email: String
)
