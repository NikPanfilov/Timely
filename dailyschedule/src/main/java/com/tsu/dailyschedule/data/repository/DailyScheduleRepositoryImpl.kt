package com.tsu.dailyschedule.data.repository

import com.tsu.dailyschedule.data.datasource.DailyScheduleDataSource
import com.tsu.dailyschedule.domain.repository.DailyScheduleRepository

class DailyScheduleRepositoryImpl(
	private val dataSource: DailyScheduleDataSource
) : DailyScheduleRepository {

	override suspend fun getDailySchedule(type:String,id: String, date: String) =
		dataSource.getDailySchedule(type,id, date)
}