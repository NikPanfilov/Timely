package com.tsu.scheduleselection.data.mapper

import com.tsu.scheduleselection.data.dto.GroupDto
import com.tsu.scheduleselection.domain.entity.Group

internal fun Group.toDto() = GroupDto(id = id, name = name)

internal fun GroupDto.toEntity() = Group(id = id, name = name)