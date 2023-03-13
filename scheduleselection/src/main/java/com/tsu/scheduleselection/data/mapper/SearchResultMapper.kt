package com.tsu.scheduleselection.data.mapper

import com.tsu.scheduleselection.data.dto.SearchResultDto
import com.tsu.scheduleselection.domain.entity.SearchResult

internal fun SearchResult.toDto() = SearchResultDto(id = id, name = name)

internal fun SearchResultDto.toEntity() = SearchResult(id = id, name = name)