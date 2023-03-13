package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.data.api.SearchApi
import com.tsu.scheduleselection.data.mapper.toEntity

class SearchDataSourceImpl(private val api: SearchApi) : SearchDataSource {

	override suspend fun search(type: String, filter: String) =
		api.search(type, filter).map { it.toEntity() }
}