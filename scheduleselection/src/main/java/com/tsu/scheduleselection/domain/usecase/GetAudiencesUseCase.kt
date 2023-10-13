package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.SearchRepository
import com.tsu.shared.entity.Audience

class GetAudiencesUseCase(private val repository: SearchRepository) {

	suspend operator fun invoke(): List<Audience> = repository.getAudiences()
}