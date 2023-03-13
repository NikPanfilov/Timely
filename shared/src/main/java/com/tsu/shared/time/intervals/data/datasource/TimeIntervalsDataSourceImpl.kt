package com.tsu.shared.time.intervals.data.datasource

import com.tsu.shared.time.intervals.data.api.TimeIntervalsApi
import com.tsu.shared.time.intervals.data.mapper.toEntity

class TimeIntervalsDataSourceImpl(private val api: TimeIntervalsApi) : TimeIntervalsDataSource {

	override suspend fun getTimeIntervals() =
		api.getTimeIntervals().map { it.toEntity() }
}