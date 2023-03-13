package com.tsu.shared.time.intervals.data.datasource

import com.tsu.shared.time.intervals.domain.entity.TimeInterval

interface TimeIntervalsDataSource {

	suspend fun getTimeIntervals(): List<TimeInterval>
}