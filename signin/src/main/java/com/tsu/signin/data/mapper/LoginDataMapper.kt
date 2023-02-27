package com.tsu.signin.data.mapper

import com.tsu.signin.data.dto.LoginDataDto
import com.tsu.signin.domain.entity.LoginData

internal fun LoginDataDto.toEntity() =
	LoginData(
		email = email,
		password = password
	)

internal fun LoginData.toDto() =
	LoginDataDto(
		email = email,
		password = password
	)