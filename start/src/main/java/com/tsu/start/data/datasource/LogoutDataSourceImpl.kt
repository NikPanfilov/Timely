package com.tsu.start.data.datasource

import com.tsu.start.data.api.LogoutApi

class LogoutDataSourceImpl(private val api: LogoutApi) : LogoutDataSource {

	override suspend fun logout() =
		api.logout()
}