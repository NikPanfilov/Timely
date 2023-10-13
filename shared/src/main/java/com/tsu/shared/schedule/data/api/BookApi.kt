package com.tsu.shared.schedule.data.api

import com.tsu.shared.schedule.data.dto.BookingDto
import retrofit2.http.Body
import retrofit2.http.POST

interface BookApi {

	@POST("api/booking")
	suspend fun book(@Body booking: BookingDto)
}