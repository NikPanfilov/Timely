package com.tsu.scheduleselection.data.repository

import com.tsu.scheduleselection.data.datasource.TeacherSearchDataSource
import com.tsu.scheduleselection.domain.repository.TeacherSearchRepository

class TeacherSearchRepositoryImpl(
	private val dataSource: TeacherSearchDataSource
) : TeacherSearchRepository {

	override suspend fun searchTeacher() =
		dataSource.searchTeachers()
}