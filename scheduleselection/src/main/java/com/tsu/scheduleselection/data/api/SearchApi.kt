package com.tsu.scheduleselection.data.api

import com.tsu.scheduleselection.data.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

	@GET("api/search/{type}")
	suspend fun search(@Path("type") type: String, @Query("filter") filter: String): List<SearchResultDto>
}