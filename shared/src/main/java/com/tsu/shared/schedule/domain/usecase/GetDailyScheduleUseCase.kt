package com.tsu.shared.schedule.domain.usecase

import android.util.Log
import com.tsu.shared.date.toFormattedString
import com.tsu.shared.entity.TimeSlot
import com.tsu.shared.schedule.domain.repository.ScheduleRepository
import java.time.LocalDate

class GetDailyScheduleUseCase(
	private val repository: ScheduleRepository,
) {

	suspend operator fun invoke(type: String, itemId: String, date: LocalDate): List<TimeSlot> =
		repository.getSchedule(type, itemId, date.toFormattedString(), getWeek = true).first {
			Log.i("debug", "${it.date} $date")
			it.date == date
		}.timeSlots
}