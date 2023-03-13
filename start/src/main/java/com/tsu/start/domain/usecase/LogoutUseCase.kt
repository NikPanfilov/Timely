package com.tsu.start.domain.usecase

import com.tsu.start.domain.repository.LogoutRepository

class LogoutUseCase(
	private val repository: LogoutRepository,
) {

	suspend operator fun invoke() =
		repository.logout()
}