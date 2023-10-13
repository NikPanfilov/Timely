package com.tsu.shared.schedule.data.mapper

import com.tsu.shared.schedule.data.dto.BookingDto
import com.tsu.shared.schedule.domain.entity.Booking

internal fun Booking.toDto(): BookingDto = BookingDto(
	date = date,
	timeSlot = timeSlot,
	audienceId = audienceId,
	description = description,
	groupsId = groupsId,
)