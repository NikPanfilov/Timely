package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.TeacherSearchRepository

class SearchTeacherUseCase(
	private val repository: TeacherSearchRepository,
) {

	suspend operator fun invoke() =
		repository.searchTeacher()
}