package com.tsu.dailyschedule.domain.usecase

import com.tsu.dailyschedule.domain.repository.DailyScheduleRepository
import com.tsu.shared.date.toFormattedString
import com.tsu.shared.entity.Lesson
import java.time.LocalDate

class GetDailyScheduleUseCase(
	private val repository: DailyScheduleRepository,
) {

	suspend operator fun invoke(type: String, id: String, date: LocalDate): List<Lesson> =
		repository.getDailySchedule(type, id, date.toFormattedString()).filter { it.date.isEqual(date) }
}