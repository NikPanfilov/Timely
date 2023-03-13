package com.tsu.start.data.mapper

import com.tsu.start.data.dto.UserDataDto
import com.tsu.start.domain.model.UserData

internal fun UserDataDto.toEntity() = UserData(type = type, id = id)