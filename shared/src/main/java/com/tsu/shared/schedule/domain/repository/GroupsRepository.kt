package com.tsu.shared.schedule.domain.repository

import com.tsu.shared.entity.Group

interface GroupsRepository {

	suspend fun getGroups(): List<Group>
}