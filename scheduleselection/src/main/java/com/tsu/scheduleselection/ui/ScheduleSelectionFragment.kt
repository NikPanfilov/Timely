package com.tsu.scheduleselection.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.scheduleselection.R
import com.tsu.scheduleselection.databinding.FragmentScheduleSelectionBinding
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.scheduleselection.ui.adapter.SearchAdapter
import com.tsu.shared.GROUPS
import com.tsu.shared.PROFESSORS
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ScheduleSelectionFragment : Fragment() {

	private val binding by lazy { FragmentScheduleSelectionBinding.inflate(layoutInflater) }
	private val viewModel: ScheduleSelectionViewModel by viewModel {
		parametersOf(
			arguments?.get(TYPE)
		)
	}

	private lateinit var adapter: SearchAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		adapter = SearchAdapter(this::clickListItem)

		binding.searchRecyclerView.layoutManager = LinearLayoutManager(this.context)
		binding.searchRecyclerView.adapter = adapter

		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope, adapter)

		binding.editTextSearch.hint = when (searchType) {
			GROUPS     -> getString(R.string.hint_group)
			PROFESSORS -> getString(R.string.hint_teacher)
			else       -> getString(R.string.hint_classroom)
		}

		return binding.root
	}

	private fun clickListItem(id: String) {
		viewModel.navigateToDailySchedule(id)
	}

	var searchType = ""

	companion object {

		private const val TYPE = "type"

		@JvmStatic
		fun newInstance(type: String) =
			ScheduleSelectionFragment().apply {
				searchType = type
				arguments = bundleOf(TYPE to type)
			}
	}
}