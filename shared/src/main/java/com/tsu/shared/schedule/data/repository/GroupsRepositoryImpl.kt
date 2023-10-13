package com.tsu.shared.schedule.data.repository

import com.tsu.shared.entity.Group
import com.tsu.shared.schedule.data.api.GroupsApi
import com.tsu.shared.schedule.data.mapper.toEntity
import com.tsu.shared.schedule.domain.repository.GroupsRepository

class GroupsRepositoryImpl(private val api: GroupsApi) : GroupsRepository {

	override suspend fun getGroups(): List<Group> =
		api.getGroups().map { it.toEntity() }
}