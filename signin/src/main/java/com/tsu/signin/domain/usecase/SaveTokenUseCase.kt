package com.tsu.signin.domain.usecase

import com.tsu.signin.domain.repository.TokenRepository

class SaveTokenUseCase(
	private val repository: TokenRepository,
) {

	operator fun invoke(token: String) = repository.save(token)
}