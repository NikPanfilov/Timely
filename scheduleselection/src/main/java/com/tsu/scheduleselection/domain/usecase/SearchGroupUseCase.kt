package com.tsu.scheduleselection.domain.usecase

import com.tsu.scheduleselection.domain.repository.GroupSearchRepository

class SearchGroupUseCase(
	private val repository: GroupSearchRepository,
) {

	suspend operator fun invoke() =
		repository.searchGroup()
}