package com.tsu.signin.domain.usecase

import com.tsu.signin.domain.entity.LoginData
import com.tsu.signin.domain.repository.LoginRepository

class LoginUseCase(
	private val repository: LoginRepository,
) {

	suspend operator fun invoke(data: LoginData) =
		repository.login(data)
}