package com.tsu.shared.network.token.domain.usecase

import com.tsu.shared.network.token.domain.repository.TokenRepository

class SaveTokenUseCase(
	private val repository: TokenRepository,
) {

	operator fun invoke(token: String) = repository.save(token)
}