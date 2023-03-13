package com.tsu.scheduleselection.data.repository

import com.tsu.scheduleselection.data.datasource.SearchDataSource
import com.tsu.scheduleselection.domain.repository.SearchRepository

class SearchRepositoryImpl(
	private val dataSource: SearchDataSource
) : SearchRepository {

	override suspend fun search(type: String, filter: String) =
		dataSource.search(type, filter)
}