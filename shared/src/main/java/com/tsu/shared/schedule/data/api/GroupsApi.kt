package com.tsu.shared.schedule.data.api

import com.tsu.shared.schedule.data.dto.GroupDto
import retrofit2.http.GET

interface GroupsApi {

	@GET("api/groups")
	suspend fun getGroups(): List<GroupDto>
}