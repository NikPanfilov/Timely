package com.tsu.start.domain.usecase

import com.tsu.start.domain.repository.UserDataRepository

class ClearUserDataUseCase(private val repository: UserDataRepository) {

	operator fun invoke() = repository.clearData()
}