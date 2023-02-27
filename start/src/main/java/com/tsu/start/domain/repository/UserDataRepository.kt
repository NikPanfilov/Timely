package com.tsu.start.domain.repository

import com.tsu.start.domain.model.UserData

interface UserDataRepository {

	fun loadData(): UserData
}