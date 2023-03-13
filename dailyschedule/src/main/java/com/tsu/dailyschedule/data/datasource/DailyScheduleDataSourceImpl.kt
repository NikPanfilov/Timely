package com.tsu.dailyschedule.data.datasource

import com.tsu.dailyschedule.data.api.DailyScheduleApi
import com.tsu.dailyschedule.data.mapper.toEntity

class DailyScheduleDataSourceImpl(private val api: DailyScheduleApi) : DailyScheduleDataSource {

	override suspend fun getDailySchedule(type:String,id: String, date: String) =
		api.getDailySchedule(type, id, date).map { it.toEntity() }
}