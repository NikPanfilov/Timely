package com.tsu.scheduleselection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.scheduleselection.domain.entity.ListItem
import com.tsu.scheduleselection.domain.entity.toListItem
import com.tsu.scheduleselection.domain.usecase.GetAudiencesUseCase
import com.tsu.scheduleselection.domain.usecase.GetGroupsUseCase
import com.tsu.scheduleselection.domain.usecase.GetProfessorsUseCase
import com.tsu.shared.AUDIENCES
import com.tsu.shared.GROUPS
import com.tsu.shared.entity.Audience
import com.tsu.shared.entity.Group
import com.tsu.shared.entity.Professor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ScheduleSelectionViewModel(
	private val router: ScheduleSelectionRouter,
	private val scheduleType: String,
	private val getAudiencesUseCase: GetAudiencesUseCase,
	private val getGroupsUseCase: GetGroupsUseCase,
	private val getProfessorsUseCase: GetProfessorsUseCase,
) : ViewModel() {

	val searchMutableFlow = MutableStateFlow<String?>("")

	val audiencesFlow = MutableStateFlow<List<Audience>>(emptyList())
	val groupsFlow = MutableStateFlow<List<Group>>(emptyList())
	val professorsFlow = MutableStateFlow<List<Professor>>(emptyList())

	init {
		viewModelScope.launch {
			audiencesFlow.value = getAudiencesUseCase()
			groupsFlow.value = getGroupsUseCase()
			professorsFlow.value = getProfessorsUseCase()
		}
		searchMutableFlow.value = ""
	}

	fun getList(filter: String): List<ListItem> =
		when (scheduleType) {
			AUDIENCES -> audiencesFlow.value.filter { it.name.contains(filter) }.map { it.toListItem() }
			GROUPS    -> groupsFlow.value.filter { it.name.contains(filter) }.map { it.toListItem() }
			else      -> professorsFlow.value.filter { it.fullName.contains(filter) }.map { it.toListItem() }
		}

	fun navigateToDailySchedule(id: String) {
		router.navigateToDailySchedule(id, scheduleType)
	}
}