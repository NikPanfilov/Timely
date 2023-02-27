package com.tsu.scheduleselection.domain.entity

data class ListItem(val id: String, val name: String)

internal fun Group.toListItem() = ListItem(id = id, name = name)

internal fun Teacher.toListItem() = ListItem(id = id, name = name)

internal fun Classroom.toListItem() = ListItem(id = id, name = name)