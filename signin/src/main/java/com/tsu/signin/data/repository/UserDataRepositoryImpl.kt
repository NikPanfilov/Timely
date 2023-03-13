package com.tsu.signin.data.repository

import com.tsu.signin.data.storage.UserDataStorage
import com.tsu.signin.domain.repository.UserDataRepository

class UserDataRepositoryImpl(
	private val storage: UserDataStorage
) : UserDataRepository {

	override fun save(type: String, id: String) = storage.save(type, id)
}