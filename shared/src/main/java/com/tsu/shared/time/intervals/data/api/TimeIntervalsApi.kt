package com.tsu.shared.time.intervals.data.api

import com.tsu.shared.time.intervals.data.dto.TimeIntervalDto
import retrofit2.http.GET

interface TimeIntervalsApi {

	@GET("api/search/timeIntervals")
	suspend fun getTimeIntervals(): List<TimeIntervalDto>
}