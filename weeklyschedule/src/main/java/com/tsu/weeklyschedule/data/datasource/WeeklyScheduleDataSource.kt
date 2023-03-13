package com.tsu.weeklyschedule.data.datasource

import com.tsu.shared.entity.Lesson

interface WeeklyScheduleDataSource {

	suspend fun getWeeklySchedule(type: String, id: String, date: String): List<Lesson>
}