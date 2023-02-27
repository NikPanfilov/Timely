package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.data.api.ClassroomSearchApi
import com.tsu.scheduleselection.data.mapper.toEntity

class ClassroomSearchDataSourceImpl(private val api: ClassroomSearchApi) : ClassroomSearchDataSource {

	override suspend fun searchClassrooms() =
		api.searchClassrooms().map { it.toEntity() }
}