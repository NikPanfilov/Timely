package com.tsu.shared.schedule.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TimeSlotDto(
	@Json(name = "type") val type: String,
	@Json(name = "lessonNumber") val lessonNumber: Int,
	@Json(name = "starts") val starts: String?,
	@Json(name = "ends") val ends: String?,
	@Json(name = "lesson") val lesson: LessonDto?,
	@Json(name = "bookedLesson") val bookedLesson: BookedDto?,
)

@JsonClass(generateAdapter = true)
data class LessonDto(
	@Json(name = "id") val id: String,
	@Json(name = "timeslot") val timeslot: Int,
	@Json(name = "dayOfWeek") val dayOfWeek: String,
	@Json(name = "startDate") val startDate: String,
	@Json(name = "endDate") val endDate: String,
	@Json(name = "lessonType") val lessonType: String,
	@Json(name = "subject") val subject: SubjectDto,
	@Json(name = "professor") val professor: ProfessorDto,
	@Json(name = "audience") val audience: AudienceDto,
	@Json(name = "groups") val groups: List<GroupDto>
)

@JsonClass(generateAdapter = true)
data class BookedDto(
	@Json(name = "id") val id: String,
	@Json(name = "timeslot") val timeslot: Int,
	@Json(name = "userId") val userId: String?,
	@Json(name = "date") val date: String,
	@Json(name = "description") val description: String,
	@Json(name = "status") val status: String,
	@Json(name = "audience") val audience: AudienceDto,
	@Json(name = "groups") val groups: List<GroupDto>
)