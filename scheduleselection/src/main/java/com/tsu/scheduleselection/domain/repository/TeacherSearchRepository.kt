package com.tsu.scheduleselection.domain.repository

import com.tsu.scheduleselection.domain.entity.Teacher

interface TeacherSearchRepository {

	suspend fun searchTeacher(): List<Teacher>
}