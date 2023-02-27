package com.tsu.scheduleselection.data.mapper

import com.tsu.scheduleselection.data.dto.ClassroomDto
import com.tsu.scheduleselection.domain.entity.Classroom

internal fun Classroom.toDto() = ClassroomDto(id = id, name = name)

internal fun ClassroomDto.toEntity() = Classroom(id = id, name = name)