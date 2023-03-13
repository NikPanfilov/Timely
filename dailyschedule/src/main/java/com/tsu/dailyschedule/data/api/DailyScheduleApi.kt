package com.tsu.dailyschedule.data.api

import com.tsu.dailyschedule.data.dto.LessonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DailyScheduleApi {

	@GET("api/schedule/{type}/{id}")
	suspend fun getDailySchedule(@Path("type") type: String, @Path("id") id: String, @Query("date") date: String): List<LessonDto>
}