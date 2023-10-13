package com.tsu.shared.schedule.data.mapper

import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Professor
import com.tsu.shared.entity.TimeSlot
import com.tsu.shared.schedule.data.dto.AudienceDto
import com.tsu.shared.schedule.data.dto.GroupDto
import com.tsu.shared.schedule.data.dto.ProfessorDto
import com.tsu.shared.schedule.data.dto.TimeSlotDto

internal fun TimeSlotDto.toLesson() =
	TimeSlot.Lesson(
		id = lesson!!.id,
		starts = starts ?: "",
		ends = ends ?: "",
		timeSlot = lessonNumber,
		audience = lesson.audience.toEntity(),
		type = lesson.lessonType,
		title = lesson.subject.name ?: "",
		groups = lesson.groups.map { it.toEntity() },
		professor = lesson.professor.toEntity(),
	)

fun GroupDto.toEntity() =
	Group(id = id, name = name)

fun ProfessorDto.toEntity() =
	Professor(id = id, fullName = fullName, shortName = shortName)

fun AudienceDto.toEntity() =
	Audience(
		id = id,
		name = name,
	)