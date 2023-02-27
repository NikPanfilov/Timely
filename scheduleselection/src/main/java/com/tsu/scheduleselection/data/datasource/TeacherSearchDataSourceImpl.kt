package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.data.api.TeacherSearchApi
import com.tsu.scheduleselection.data.mapper.toEntity

class TeacherSearchDataSourceImpl(private val api: TeacherSearchApi) : TeacherSearchDataSource {

	override suspend fun searchTeachers() =
		api.searchTeachers().map { it.toEntity() }
}