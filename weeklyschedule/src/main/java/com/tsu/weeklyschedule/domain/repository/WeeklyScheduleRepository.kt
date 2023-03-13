package com.tsu.weeklyschedule.domain.repository

import com.tsu.shared.entity.Lesson

interface WeeklyScheduleRepository {

	suspend fun getWeeklySchedule(type:String,id: String, date: String): List<Lesson>
}