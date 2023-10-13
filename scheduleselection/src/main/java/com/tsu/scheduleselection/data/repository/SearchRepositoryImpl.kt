package com.tsu.scheduleselection.data.repository

import com.tsu.scheduleselection.data.api.SearchApi
import com.tsu.scheduleselection.domain.repository.SearchRepository
import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Professor
import com.tsu.shared.schedule.data.mapper.toEntity

class SearchRepositoryImpl(
	private val api: SearchApi
) : SearchRepository {

	override suspend fun getAudiences(): List<Audience> =
		api.getAudiences().map { it.toEntity() }

	override suspend fun getGroups(): List<Group> =
		api.getGroups().map { it.toEntity() }

	override suspend fun getProfessors(): List<Professor> =
		api.getProfessors().map { it.toEntity() }

}