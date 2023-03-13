package com.tsu.signup.domain.repository

import com.tsu.signup.domain.entity.TokenResponse
import com.tsu.signup.domain.entity.UserRegisterData

interface RegistrationRepository {

	suspend fun registration(data: UserRegisterData): TokenResponse
}