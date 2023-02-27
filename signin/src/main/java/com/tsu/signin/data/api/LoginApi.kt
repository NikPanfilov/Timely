package com.tsu.signin.data.api

import com.tsu.signin.data.dto.LoginDataDto
import com.tsu.signin.data.dto.TokenResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

	@POST("api/account/login")
	suspend fun login(@Body data: LoginDataDto): TokenResponseDto
}