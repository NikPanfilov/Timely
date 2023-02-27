package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.domain.entity.Group

interface GroupSearchDataSource {

	suspend fun searchGroups(): List<Group>
}