package com.tsu.weeklyschedule.data.datasource

import com.tsu.weeklyschedule.data.api.WeeklyScheduleApi
import com.tsu.weeklyschedule.data.mapper.toEntity

class WeeklyScheduleDataSourceImpl(private val api: WeeklyScheduleApi) : WeeklyScheduleDataSource {

	override suspend fun getWeeklySchedule(type: String, id: String, date: String) =
		api.getWeeklySchedule(type, id, date).map { it.toEntity() }
}