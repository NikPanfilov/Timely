package com.tsu.scheduleselection.domain.entity

data class ListItem(val id: String, val name: String)

internal fun SearchResult.toListItem() = ListItem(id = id, name = name)