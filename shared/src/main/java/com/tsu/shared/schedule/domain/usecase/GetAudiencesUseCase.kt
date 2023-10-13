package com.tsu.shared.schedule.domain.usecase

import com.tsu.shared.entity.Audience
import com.tsu.shared.schedule.domain.repository.AudienceRepository
import java.time.LocalDate

class GetAudiencesUseCase(private val repository: AudienceRepository) {

	suspend operator fun invoke(date: LocalDate, timeSlot: Int): List<Audience> = repository.getAudiences(date, timeSlot)
}