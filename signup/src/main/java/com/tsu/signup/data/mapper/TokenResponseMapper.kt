package com.tsu.signup.data.mapper

import com.tsu.signup.data.dto.TokenResponseDto
import com.tsu.signup.domain.entity.TokenResponse

internal fun TokenResponseDto.toEntity() =
	TokenResponse(
		email = email,
		token = token,
		role = role,
		group = group,
		teacher = teacher
	)