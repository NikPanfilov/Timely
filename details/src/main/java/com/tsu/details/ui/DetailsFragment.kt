package com.tsu.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.details.databinding.FragmentDetailsBinding
import com.tsu.details.presentation.DetailsViewModel
import com.tsu.details.ui.adapter.GroupsAdapter
import com.tsu.shared.GROUPS
import com.tsu.shared.navigation.holder.LessonHolder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

	companion object {

		private const val LESSON = "lessonHolder"

		fun newInstance(lesson: LessonHolder) = DetailsFragment().apply {
			arguments = bundleOf(LESSON to lesson)
		}
	}

	private val binding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }
	private val viewModel: DetailsViewModel by viewModel {
		parametersOf(
			arguments?.get(LESSON)
		)
	}

	private lateinit var adapter: GroupsAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		adapter = GroupsAdapter(this::toGroupSchedule)
		binding.groupsList.layoutManager = LinearLayoutManager(this.context)
		binding.groupsList.adapter = adapter

		binding.bindData(viewModel, adapter)

		return binding.root
	}

	private fun toGroupSchedule(id: String) {
		viewModel.navigateToDailySchedule(id, GROUPS)
	}
}