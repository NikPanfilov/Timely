package com.tsu.scheduleselection.domain.repository

import com.tsu.scheduleselection.domain.entity.Group

interface GroupSearchRepository {

	suspend fun searchGroup(): List<Group>
}