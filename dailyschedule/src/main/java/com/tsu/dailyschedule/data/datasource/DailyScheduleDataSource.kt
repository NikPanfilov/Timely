package com.tsu.dailyschedule.data.datasource

import com.tsu.shared.entity.Lesson

interface DailyScheduleDataSource {

	suspend fun getDailySchedule(type:String,id: String, date: String): List<Lesson>
}