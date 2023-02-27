package com.tsu.scheduleselection.data.mapper

import com.tsu.scheduleselection.data.dto.TeacherDto
import com.tsu.scheduleselection.domain.entity.Teacher

internal fun Teacher.toDto() = TeacherDto(id = id, name = name)

internal fun TeacherDto.toEntity() = Teacher(id = id, name = name)