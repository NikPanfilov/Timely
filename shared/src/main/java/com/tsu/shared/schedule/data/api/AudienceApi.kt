package com.tsu.shared.schedule.data.api

import com.tsu.shared.schedule.data.dto.AudienceDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AudienceApi {

	@GET("api/booking/available-audiences")
	suspend fun getAudiences(
		@Query("date") date: String,
		@Query("timeslot") timeSlot: Int,
	): List<AudienceDto>
}