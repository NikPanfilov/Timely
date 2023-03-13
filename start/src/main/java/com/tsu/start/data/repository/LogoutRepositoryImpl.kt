package com.tsu.start.data.repository

import com.tsu.start.data.datasource.LogoutDataSource
import com.tsu.start.domain.repository.LogoutRepository

class LogoutRepositoryImpl(
	private val dataSource: LogoutDataSource
) : LogoutRepository {

	override suspend fun logout() = dataSource.logout()
}