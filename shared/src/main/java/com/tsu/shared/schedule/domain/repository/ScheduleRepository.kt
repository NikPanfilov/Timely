package com.tsu.shared.schedule.domain.repository

import com.tsu.shared.schedule.domain.entity.ScheduleDay

interface ScheduleRepository {

	suspend fun getSchedule(type: String, itemId: String, date: String, getWeek: Boolean): List<ScheduleDay>
}