package com.tsu.signup.data.datasource

import com.tsu.signup.data.api.RegistrationApi
import com.tsu.signup.data.mapper.toDto
import com.tsu.signup.data.mapper.toEntity
import com.tsu.signup.domain.entity.UserRegisterData

class RegistrationDataSourceImpl(private val api: RegistrationApi) : RegistrationDataSource {

	override suspend fun registration(data: UserRegisterData) =
		api.registration(data.toDto()).toEntity()
}