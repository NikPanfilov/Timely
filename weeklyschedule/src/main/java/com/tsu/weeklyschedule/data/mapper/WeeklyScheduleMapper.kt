package com.tsu.weeklyschedule.data.mapper

import com.tsu.shared.date.toLocalDate
import com.tsu.shared.entity.Classroom
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Lesson
import com.tsu.shared.entity.Name
import com.tsu.shared.entity.Tag
import com.tsu.shared.entity.Teacher
import com.tsu.shared.entity.TimeInterval
import com.tsu.weeklyschedule.data.dto.WeeklyScheduleDto

internal fun WeeklyScheduleDto.toEntity() =
	Lesson(
		name = Name(name.name, name.id),
		group = group.map { Group(it.name, it.id) },
		classroom = Classroom(classroom.name, classroom.id),
		date = date.toLocalDate(),
		tag = Tag(tag.name, tag.id),
		timeInterval = TimeInterval(timeInterval.startTime, timeInterval.endTime, timeInterval.id),
		teacher = Teacher(teacher.name, teacher.id),
		id = id
	)