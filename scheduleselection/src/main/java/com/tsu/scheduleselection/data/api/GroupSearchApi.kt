package com.tsu.scheduleselection.data.api

import com.tsu.scheduleselection.data.dto.GroupDto
import retrofit2.http.GET

interface GroupSearchApi {

	//TODO:Добавить текст для фильтрации
	@GET("api/search/groups")
	suspend fun searchGroups(): List<GroupDto>
}