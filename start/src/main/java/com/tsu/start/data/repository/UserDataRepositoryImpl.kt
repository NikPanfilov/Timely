package com.tsu.start.data.repository

import com.tsu.start.data.datasource.UserDataStorage
import com.tsu.start.domain.model.UserData
import com.tsu.start.domain.repository.UserDataRepository

class UserDataRepositoryImpl(private val userDataStorage: UserDataStorage) : UserDataRepository {

	override fun loadData(): UserData {
		val userData = userDataStorage.loadData()
		return UserData(
			token = userData.token,
			type = userData.type,
			name = userData.name
		)
	}
}