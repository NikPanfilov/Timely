package com.tsu.signin.domain.repository

import com.tsu.signin.domain.entity.LoginData
import com.tsu.signin.domain.entity.TokenResponse

interface LoginRepository {

	suspend fun login(data: LoginData): TokenResponse
}