package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.data.api.GroupSearchApi
import com.tsu.scheduleselection.data.mapper.toEntity

class GroupSearchDataSourceImpl(private val api: GroupSearchApi) : GroupSearchDataSource {

	override suspend fun searchGroups() =
		api.searchGroups().map { it.toEntity() }
}