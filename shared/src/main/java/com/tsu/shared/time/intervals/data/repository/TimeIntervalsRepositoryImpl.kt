package com.tsu.shared.time.intervals.data.repository

import com.tsu.shared.time.intervals.data.datasource.TimeIntervalsDataSource
import com.tsu.shared.time.intervals.domain.repository.TimeIntervalsRepository

class TimeIntervalsRepositoryImpl(
	private val dataSource: TimeIntervalsDataSource
) : TimeIntervalsRepository {

	override suspend fun getTimeIntervals() = dataSource.getTimeIntervals()
}