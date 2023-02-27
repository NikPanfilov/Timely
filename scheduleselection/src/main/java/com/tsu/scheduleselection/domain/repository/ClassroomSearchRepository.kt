package com.tsu.scheduleselection.domain.repository

import com.tsu.scheduleselection.domain.entity.Classroom

interface ClassroomSearchRepository {

	suspend fun searchClassroom(): List<Classroom>
}