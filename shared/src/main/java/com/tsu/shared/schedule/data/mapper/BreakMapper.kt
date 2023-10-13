package com.tsu.shared.schedule.data.mapper

import com.tsu.shared.date.toLocalDate
import com.tsu.shared.entity.TimeSlot
import com.tsu.shared.schedule.data.dto.TimeSlotDto

fun TimeSlotDto.toBreak(date: String) =
	TimeSlot.Break(
		type = type,
		starts = starts ?: "",
		ends = ends ?: "",
		timeSlot = lessonNumber,
		date = date.toLocalDate(),
	)

fun TimeSlotDto.toBooked() =
	TimeSlot.Booked(
		type = type,
		starts = starts ?: "",
		ends = ends ?: "",
		timeSlot = lessonNumber,
		audience = bookedLesson!!.audience.toEntity(),
		groups = bookedLesson.groups.map { it.toEntity() },
		description = bookedLesson.description,
	)