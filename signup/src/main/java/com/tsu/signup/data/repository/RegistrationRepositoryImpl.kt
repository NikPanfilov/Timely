package com.tsu.signup.data.repository

import com.tsu.signup.data.datasource.RegistrationDataSource
import com.tsu.signup.domain.entity.UserRegisterData
import com.tsu.signup.domain.repository.RegistrationRepository

class RegistrationRepositoryImpl(
	private val dataSource: RegistrationDataSource
) : RegistrationRepository {

	override suspend fun registration(data: UserRegisterData) = dataSource.registration(data)
}