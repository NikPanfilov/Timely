package com.tsu.scheduleselection.data.repository

import com.tsu.scheduleselection.data.datasource.ClassroomSearchDataSource
import com.tsu.scheduleselection.domain.repository.ClassroomSearchRepository

class ClassroomSearchRepositoryImpl(
	private val dataSource: ClassroomSearchDataSource
) : ClassroomSearchRepository {

	override suspend fun searchClassroom() =
		dataSource.searchClassrooms()
}