package com.tsu.signin.data.mapper

import com.tsu.signin.data.dto.TokenResponseDto
import com.tsu.signin.domain.entity.TokenResponse

internal fun TokenResponseDto.toEntity() =
	TokenResponse(
		email = email,
		token = token,
		role = role
	)

internal fun TokenResponse.toDto() =
	TokenResponseDto(
		email = email,
		token = token,
		role = role
	)