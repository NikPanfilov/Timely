package com.tsu.scheduleselection.data.api

import com.tsu.shared.schedule.data.dto.AudienceDto
import com.tsu.shared.schedule.data.dto.GroupDto
import com.tsu.shared.schedule.data.dto.ProfessorDto
import retrofit2.http.GET

interface SearchApi {

	@GET("api/audiences")
	suspend fun getAudiences(): List<AudienceDto>

	@GET("api/groups")
	suspend fun getGroups(): List<GroupDto>

	@GET("api/professors")
	suspend fun getProfessors(): List<ProfessorDto>
}