package com.tsu.signin.data.datasource

import com.tsu.signin.data.api.LoginApi
import com.tsu.signin.data.mapper.toDto
import com.tsu.signin.data.mapper.toEntity
import com.tsu.signin.domain.entity.LoginData

class LoginDataSourceImpl(private val api: LoginApi) : LoginDataSource {

	override suspend fun login(data: LoginData) =
		api.login(data.toDto()).toEntity()
}