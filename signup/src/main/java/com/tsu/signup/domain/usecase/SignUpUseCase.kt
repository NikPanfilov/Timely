package com.tsu.signup.domain.usecase

import com.tsu.signup.domain.entity.UserRegisterData
import com.tsu.signup.domain.repository.RegistrationRepository

class SignUpUseCase(
	private val repository: RegistrationRepository,
) {

	suspend operator fun invoke(data: UserRegisterData) =
		repository.registration(data)
}