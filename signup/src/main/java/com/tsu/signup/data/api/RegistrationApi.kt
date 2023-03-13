package com.tsu.signup.data.api

import com.tsu.signup.data.dto.TokenResponseDto
import com.tsu.signup.data.dto.UserRegisterDataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
	@POST("api/account/login")
	suspend fun login(@Body data: UserRegisterDataDto): TokenResponseDto
}