package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.SearchRepository
import com.tsu.shared.entity.Professor

class GetProfessorsUseCase(private val repository: SearchRepository) {

	suspend operator fun invoke(): List<Professor> = repository.getProfessors()
}