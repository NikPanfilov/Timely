package com.tsu.start.data.repository

import com.tsu.start.data.mapper.toEntity
import com.tsu.start.data.storage.UserDataStorage
import com.tsu.start.domain.repository.UserDataRepository

class UserDataRepositoryImpl(private val dataStorage: UserDataStorage) : UserDataRepository {

	override fun loadData() = dataStorage.loadData().toEntity()
	override fun clearData() = dataStorage.clearData()
}