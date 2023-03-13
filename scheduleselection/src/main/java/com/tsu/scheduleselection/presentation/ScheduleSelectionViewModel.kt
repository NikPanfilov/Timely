package com.tsu.scheduleselection.presentation

import androidx.lifecycle.ViewModel
import com.tsu.scheduleselection.domain.entity.ListItem
import com.tsu.scheduleselection.domain.entity.toListItem
import com.tsu.scheduleselection.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class ScheduleSelectionViewModel(
	private val router: ScheduleSelectionRouter,
	private val scheduleType: String,
	private val searchUseCase: SearchUseCase
) : ViewModel() {

	val searchMutableFlow = MutableStateFlow<String?>("")

	init {

	}

	suspend fun getList(filter: String): List<ListItem> {

		return searchUseCase.invoke(scheduleType, filter).map { it.toListItem() }
	}

	fun navigateToDailySchedule(id: String) {
		router.navigateToDailySchedule(id, scheduleType)
	}
}