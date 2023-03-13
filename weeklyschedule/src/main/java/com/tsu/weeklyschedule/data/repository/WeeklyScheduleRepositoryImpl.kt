package com.tsu.weeklyschedule.data.repository

import com.tsu.weeklyschedule.data.datasource.WeeklyScheduleDataSource
import com.tsu.weeklyschedule.domain.repository.WeeklyScheduleRepository

class WeeklyScheduleRepositoryImpl(
	private val dataSource: WeeklyScheduleDataSource
) : WeeklyScheduleRepository {

	override suspend fun getWeeklySchedule(type:String,id: String, date: String) =
		dataSource.getWeeklySchedule(type,id, date)
}