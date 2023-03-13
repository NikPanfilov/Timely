package com.tsu.start.data.storage

import com.tsu.start.data.dto.UserDataDto

interface UserDataStorage {

	fun loadData(): UserDataDto

	fun clearData()
}