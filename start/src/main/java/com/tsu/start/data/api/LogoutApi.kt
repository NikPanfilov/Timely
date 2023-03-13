package com.tsu.start.data.api

import retrofit2.http.POST

interface LogoutApi {

	@POST("api/account/logout")
	suspend fun logout()
}