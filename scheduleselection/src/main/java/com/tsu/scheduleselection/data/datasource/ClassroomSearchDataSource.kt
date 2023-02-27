package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.domain.entity.Classroom

interface ClassroomSearchDataSource {

	suspend fun searchClassrooms(): List<Classroom>
}