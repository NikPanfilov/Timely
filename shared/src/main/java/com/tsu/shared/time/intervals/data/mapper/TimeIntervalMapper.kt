package com.tsu.shared.time.intervals.data.mapper

import com.tsu.shared.time.intervals.data.dto.TimeIntervalDto
import com.tsu.shared.time.intervals.domain.entity.TimeInterval

internal fun TimeIntervalDto.toEntity() = TimeInterval(id = id, startTime = startTime, endTime = endTime)