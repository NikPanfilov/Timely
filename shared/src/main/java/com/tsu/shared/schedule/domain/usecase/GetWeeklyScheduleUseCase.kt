package com.tsu.shared.schedule.domain.usecase

import com.tsu.shared.date.toFormattedString
import com.tsu.shared.schedule.domain.entity.ScheduleDay
import com.tsu.shared.schedule.domain.repository.ScheduleRepository
import java.time.LocalDate

class GetWeeklyScheduleUseCase(
	private val repository: ScheduleRepository,
) {

	suspend operator fun invoke(type: String, itemId: String, date: LocalDate): List<ScheduleDay> =
		repository.getSchedule(type, itemId, date.toFormattedString(), getWeek = true).sortedBy { it.date }
}