package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.ClassroomSearchRepository

class SearchClassroomUseCase(
	private val repository: ClassroomSearchRepository,
) {

	suspend operator fun invoke() =
		repository.searchClassroom()
}