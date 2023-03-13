package com.tsu.scheduleselection.ui

import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.scheduleselection.databinding.FragmentScheduleSelectionBinding
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.scheduleselection.ui.adapter.SearchAdapter
import com.tsu.shared.binding.bindText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

internal fun FragmentScheduleSelectionBinding.bindData(viewModel: ScheduleSelectionViewModel, scope: LifecycleCoroutineScope, listAdapter: SearchAdapter) {
	editTextSearch.bindText(scope, viewModel.searchMutableFlow)

	scope.launchWhenResumed {
		viewModel.searchMutableFlow.onEach {
			listAdapter.data = viewModel.getList(it ?: "")
		}.collect()
	}
}