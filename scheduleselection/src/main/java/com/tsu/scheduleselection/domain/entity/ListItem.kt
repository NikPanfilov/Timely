package com.tsu.scheduleselection.domain.entity

import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Professor

data class ListItem(val id: String, val name: String)

internal fun Audience.toListItem() = ListItem(id = id ?: "", name = name)
internal fun Group.toListItem() = ListItem(id = id ?: "", name = name)
internal fun Professor.toListItem() = ListItem(id = id ?: "", name = fullName)