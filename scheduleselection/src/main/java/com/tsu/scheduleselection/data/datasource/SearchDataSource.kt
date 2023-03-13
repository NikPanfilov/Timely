package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.domain.entity.SearchResult

interface SearchDataSource {

	suspend fun search(type: String, filter: String): List<SearchResult>
}