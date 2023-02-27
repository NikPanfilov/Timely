package com.tsu.scheduleselection.data.datasource

import com.tsu.scheduleselection.domain.entity.Teacher

interface TeacherSearchDataSource {

	suspend fun searchTeachers(): List<Teacher>
}