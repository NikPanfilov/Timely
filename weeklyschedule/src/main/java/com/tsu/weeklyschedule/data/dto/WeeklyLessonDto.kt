package com.tsu.weeklyschedule.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeeklyScheduleDto(
	@Json(name = "name") val name: Name,
	@Json(name = "tag") val tag: Tag,
	@Json(name = "group") val group: List<Group>,
	@Json(name = "teacher") val teacher: Teacher,
	@Json(name = "timeInterval") val timeInterval: TimeInterval,
	@Json(name = "date") val date: String,
	@Json(name = "classroom") val classroom: Classroom,
	@Json(name = "id") val id: String?,
	@Json(name = "chainId") val chainId: String?
)

@JsonClass(generateAdapter = true)
data class Name(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?
)

@JsonClass(generateAdapter = true)
data class Tag(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?
)

@JsonClass(generateAdapter = true)
data class Group(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?
)

@JsonClass(generateAdapter = true)
data class Teacher(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?
)

@JsonClass(generateAdapter = true)
data class TimeInterval(
	@Json(name = "startTime") val startTime: String,
	@Json(name = "endTime") val endTime: String,
	@Json(name = "id") val id: String?
)

@JsonClass(generateAdapter = true)
data class Classroom(
	@Json(name = "name") val name: String,
	@Json(name = "id") val id: String?
)



