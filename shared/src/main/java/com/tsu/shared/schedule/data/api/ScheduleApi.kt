package com.tsu.shared.schedule.data.api

import com.tsu.shared.schedule.data.dto.ScheduleDayDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

	@GET("lessons/search/{type}")
	suspend fun getSchedule(
		@Path("type") type: String,
		@Query("filterItemId") itemId: String,
		@Query("day") date: String,
		@Query("getWeek") getWeek: Boolean,
		@Query("withBreaks") withBreaks: Boolean,
	): List<ScheduleDayDto>
}