package com.tsu.scheduleselection.domain.repository

import com.tsu.scheduleselection.domain.entity.SearchResult

interface SearchRepository {

	suspend fun search(type: String, filter: String): List<SearchResult>
}