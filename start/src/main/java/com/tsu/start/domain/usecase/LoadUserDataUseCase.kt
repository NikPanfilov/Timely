package com.tsu.start.domain.usecase

import com.tsu.start.domain.model.UserData
import com.tsu.start.domain.repository.UserDataRepository

class LoadUserDataUseCase(private val repository: UserDataRepository) {

	operator fun invoke(): UserData = repository.loadData()
}