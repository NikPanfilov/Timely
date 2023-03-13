package com.tsu.signup.data.mapper

import com.tsu.signup.data.dto.UserRegisterDataDto
import com.tsu.signup.domain.entity.UserRegisterData

internal fun UserRegisterDataDto.toEntity() =
	UserRegisterData(
		email = email,
		password = password,
		fullName = fullName
	)

internal fun UserRegisterData.toDto() =
	UserRegisterDataDto(
		email = email,
		password = password,
		fullName = fullName
	)