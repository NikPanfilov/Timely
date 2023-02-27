package com.tsu.scheduleselection.data.repository

import com.tsu.scheduleselection.data.datasource.GroupSearchDataSource
import com.tsu.scheduleselection.domain.repository.GroupSearchRepository

class GroupSearchRepositoryImpl(
	private val dataSource: GroupSearchDataSource
) : GroupSearchRepository {

	override suspend fun searchGroup() =
		dataSource.searchGroups()
}