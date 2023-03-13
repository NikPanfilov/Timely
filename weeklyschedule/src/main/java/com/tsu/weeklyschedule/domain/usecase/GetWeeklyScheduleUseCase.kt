package com.tsu.weeklyschedule.domain.usecase

import com.tsu.shared.date.toFormattedString
import com.tsu.weeklyschedule.domain.repository.WeeklyScheduleRepository
import java.time.LocalDate

class GetWeeklyScheduleUseCase(
	private val repository: WeeklyScheduleRepository,
) {

	suspend operator fun invoke(type: String, id: String, date: LocalDate) =
		repository.getWeeklySchedule(type, id, date.toFormattedString())
}