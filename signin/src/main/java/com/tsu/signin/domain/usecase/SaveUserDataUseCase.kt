package com.tsu.signin.domain.usecase

import com.tsu.signin.domain.repository.UserDataRepository

class SaveUserDataUseCase(
	private val repository: UserDataRepository,
) {

	operator fun invoke(type: String, id: String) =
		repository.save(type, id)
}