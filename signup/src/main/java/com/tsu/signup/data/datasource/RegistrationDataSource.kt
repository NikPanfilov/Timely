package com.tsu.signup.data.datasource

import com.tsu.signup.domain.entity.TokenResponse
import com.tsu.signup.domain.entity.UserRegisterData

interface RegistrationDataSource {

	suspend fun registration(data: UserRegisterData): TokenResponse
}