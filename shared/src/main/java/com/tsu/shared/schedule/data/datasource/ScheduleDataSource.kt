package com.tsu.shared.schedule.data.datasource

import com.tsu.shared.schedule.domain.entity.ScheduleDay

interface ScheduleDataSource {

	suspend fun getSchedule(type: String, itemId: String, date: String, getWeek: Boolean): List<ScheduleDay>
}