package com.tsu.shared.schedule.data.mapper

import android.util.Log
import com.tsu.shared.date.toLocalDate
import com.tsu.shared.schedule.data.dto.ScheduleDayDto
import com.tsu.shared.schedule.domain.entity.ScheduleDay

internal fun ScheduleDayDto.toEntity() =
	ScheduleDay(
		date = date.toLocalDate(),
		timeSlots = timeSlots.map {
			Log.i("debug", "s")
			if (it.lesson != null) {
				Log.i("debug", "l")
				it.toLesson()
			} else {
				if (it.type == "Empty") {
					Log.i("debug", "e")
					it.toBreak(date)
				} else {
					Log.i("debug", "b")
					it.toBooked()
				}
			}
		}
	)