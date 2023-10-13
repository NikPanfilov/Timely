package com.tsu.shared.schedule.data.datasource

import android.util.Log
import com.tsu.shared.schedule.data.api.ScheduleApi
import com.tsu.shared.schedule.data.mapper.toEntity
import com.tsu.shared.schedule.domain.entity.ScheduleDay

class ScheduleDataSourceImpl(private val api: ScheduleApi) : ScheduleDataSource {

	override suspend fun getSchedule(type: String, itemId: String, date: String, getWeek: Boolean): List<ScheduleDay> {
		val x = api.getSchedule(type, itemId, date, getWeek, false)
		Log.i("debug", x.toString())
		return x.map {
			it.toEntity()
		}
	}
}