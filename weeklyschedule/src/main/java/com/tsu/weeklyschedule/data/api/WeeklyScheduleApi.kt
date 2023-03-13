package com.tsu.weeklyschedule.data.api

import com.tsu.weeklyschedule.data.dto.WeeklyScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeeklyScheduleApi {

	@GET("api/schedule/{type}/{id}")
	suspend fun getWeeklySchedule(@Path("type") type: String, @Path("id") id: String, @Query("date") date: String): List<WeeklyScheduleDto>
}