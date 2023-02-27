package com.tsu.scheduleselection.data.api

import com.tsu.scheduleselection.data.dto.ClassroomDto
import retrofit2.http.GET

interface ClassroomSearchApi {

	//TODO:Добавить текст для фильтрации
	@GET("api/search/classrooms")
	suspend fun searchClassrooms(): List<ClassroomDto>
}