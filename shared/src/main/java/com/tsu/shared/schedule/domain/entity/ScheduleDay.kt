package com.tsu.shared.schedule.domain.entity

import com.tsu.shared.entity.TimeSlot
import java.time.LocalDate

data class ScheduleDay(

	val date: LocalDate,
	val timeSlots: List<TimeSlot>,
)
