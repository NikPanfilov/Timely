package com.tsu.scheduleselection.presentation

import androidx.lifecycle.ViewModel
import com.tsu.scheduleselection.domain.entity.ListItem
import com.tsu.scheduleselection.domain.entity.toListItem
import com.tsu.scheduleselection.domain.usecase.SearchClassroomUseCase
import com.tsu.scheduleselection.domain.usecase.SearchGroupUseCase
import com.tsu.scheduleselection.domain.usecase.SearchTeacherUseCase
import com.tsu.shared.ScheduleType
import com.tsu.shared.Type
import kotlinx.coroutines.flow.MutableStateFlow

class ScheduleSelectionViewModel(
	private val router: ScheduleSelectionRouter,
	private val scheduleType: ScheduleType,
	private val searchGroup: SearchGroupUseCase,
	private val searchTeacher: SearchTeacherUseCase,
	private val searchClassroom: SearchClassroomUseCase
) : ViewModel() {

	val searchMutableFlow = MutableStateFlow<String?>("")

	init {

	}

	suspend fun getList(): List<ListItem> =
		when (scheduleType.type) {
			is Type.Group     -> searchGroup.invoke().map { it.toListItem() }
			is Type.Teacher   -> searchTeacher.invoke().map { it.toListItem() }
			is Type.Classroom -> searchClassroom.invoke().map { it.toListItem() }
		}
}