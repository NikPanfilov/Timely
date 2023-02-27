package com.tsu.signin.data.repository

import com.tsu.signin.data.datasource.LoginDataSource
import com.tsu.signin.domain.entity.LoginData
import com.tsu.signin.domain.repository.LoginRepository

class LoginRepositoryImpl(
	private val dataSource: LoginDataSource
) : LoginRepository {

	override suspend fun login(data: LoginData) =
		dataSource.login(data)
}