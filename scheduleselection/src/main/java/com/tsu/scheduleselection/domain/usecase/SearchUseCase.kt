package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.SearchRepository

class SearchUseCase(
	private val repository: SearchRepository,
) {

	suspend operator fun invoke(type: String, filter: String) =
		repository.search(type, filter)
}