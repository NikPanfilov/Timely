package com.tsu.signup.data.mapper

import com.tsu.signup.data.dto.GroupDto
import com.tsu.signup.data.dto.TeacherDto
import com.tsu.signup.data.dto.TokenResponseDto
import com.tsu.signup.domain.entity.Group
import com.tsu.signup.domain.entity.Teacher
import com.tsu.signup.domain.entity.TokenResponse

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