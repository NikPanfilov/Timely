package com.tsu.dailyschedule.domain.repository

import com.tsu.shared.entity.Lesson

interface DailyScheduleRepository {

	suspend fun getDailySchedule(type:String,id: String, date: String): List<Lesson>
}