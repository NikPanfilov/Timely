package com.tsu.shared.schedule.data.repository

import com.tsu.shared.schedule.data.datasource.ScheduleDataSource
import com.tsu.shared.schedule.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(
	private val dataSource: ScheduleDataSource
) : ScheduleRepository {

	override suspend fun getSchedule(type: String, itemId: String, date: String, getWeek: Boolean) =
		dataSource.getSchedule(type, itemId, date, getWeek)
}