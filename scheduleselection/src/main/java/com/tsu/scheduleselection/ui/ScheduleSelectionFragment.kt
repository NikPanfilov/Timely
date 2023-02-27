package com.tsu.scheduleselection.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.scheduleselection.databinding.FragmentScheduleSelectionBinding
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.scheduleselection.ui.adapter.SearchAdapter
import com.tsu.shared.ScheduleType
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		adapter = SearchAdapter(this::clickListItem) // Создание объекта
		//adapter.data = personService.getPersons() // Заполнение данными

		binding.searchRecyclerView.layoutManager = LinearLayoutManager(this.context) // Назначение LayoutManager для RecyclerView
		binding.searchRecyclerView.adapter = adapter // Назначение адаптера для RecyclerView

		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope, adapter)

		// Inflate the layout for this fragment
		return binding.root
	}

	private fun clickListItem(text: String) {
		Log.i("work?", text)
	}

	companion object {

		private const val TYPE = "type"

		@JvmStatic
		fun newInstance(type: ScheduleType) =
			ScheduleSelectionFragment().apply {
				arguments = bundleOf(TYPE to type)
			}
	}
}