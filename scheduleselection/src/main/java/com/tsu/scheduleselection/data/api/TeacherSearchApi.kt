package com.tsu.scheduleselection.data.api

import com.tsu.scheduleselection.data.dto.TeacherDto
import retrofit2.http.GET

interface TeacherSearchApi {

	//TODO:Добавить текст для фильтрации
	@GET("api/search/teachers")
	suspend fun searchTeachers(): List<TeacherDto>
}