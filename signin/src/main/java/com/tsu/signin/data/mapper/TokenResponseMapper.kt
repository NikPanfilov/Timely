package com.tsu.signin.data.mapper

import com.tsu.signin.data.dto.GroupDto
import com.tsu.signin.data.dto.TeacherDto
import com.tsu.signin.data.dto.TokenResponseDto
import com.tsu.signin.domain.entity.Group
import com.tsu.signin.domain.entity.Teacher
import com.tsu.signin.domain.entity.TokenResponse

internal fun TokenResponseDto.toEntity() =
	TokenResponse(
		email = email,
		token = token,
		role = role,
		group = group?.toEntity(),
		teacher = teacher?.toEntity()
	)

internal fun GroupDto.toEntity() = Group(name = name, id = id)

internal fun TeacherDto.toEntity() = Teacher(name = name, id = id)