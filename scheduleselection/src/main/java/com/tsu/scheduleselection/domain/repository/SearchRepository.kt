package com.tsu.scheduleselection.domain.repository

import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Professor

interface SearchRepository {

	suspend fun getAudiences(): List<Audience>

	suspend fun getGroups(): List<Group>

	suspend fun getProfessors(): List<Professor>
}