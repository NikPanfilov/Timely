package com.tsu.signin.data.datasource

import com.tsu.signin.domain.entity.LoginData
import com.tsu.signin.domain.entity.TokenResponse

interface LoginDataSource {

	suspend fun login(data: LoginData): TokenResponse
}