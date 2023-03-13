package com.tsu.shared.time.intervals.domain.repository

import com.tsu.shared.time.intervals.domain.entity.TimeInterval

interface TimeIntervalsRepository {

	suspend fun getTimeIntervals(): List<TimeInterval>
}