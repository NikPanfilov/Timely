package com.tsu.dailyschedule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.dailyschedule.R
import com.tsu.dailyschedule.databinding.FragmentDailyScheduleBinding
import com.tsu.dailyschedule.presentation.DailyScheduleViewModel
import com.tsu.dailyschedule.ui.adapter.ScheduleAdapter
import com.tsu.shared.entity.Lesson
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DailyScheduleFragment : Fragment() {

	companion object {

		private const val ID = "id"
		private const val TYPE = "type"

		fun newInstance(id: String, type: String) = DailyScheduleFragment().apply {
			arguments = bundleOf(ID to id, TYPE to type)
		}
	}

	private val binding by lazy { FragmentDailyScheduleBinding.inflate(layoutInflater) }

	private lateinit var adapter: ScheduleAdapter

	private val viewModel: DailyScheduleViewModel by viewModel {
		parametersOf(
			arguments?.get(ID), arguments?.get(TYPE)
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewModel.init()

		val gestureDetector = GestureDetectorCompat(requireContext(), GestureListener(viewModel))
		adapter = ScheduleAdapter(this::clickListItem, this::getColors, gestureDetector)

		binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(this.context)
		binding.scheduleRecyclerView.adapter = adapter

		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope, adapter)
		binding.updateButtons(viewModel.date)
		binding.noLessonImageView.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }

		return binding.root
	}

	private fun clickListItem(lesson: Lesson) {
		viewModel.navigateToDetails(lesson)
	}

	private fun getColors(type: String) = when (type) {
		getString(R.string.laboratory) -> getDrawable(R.color.laboratory, R.color.laboratory_text)

		getString(R.string.lecture)    -> getDrawable(R.color.lecture, R.color.lecture_text)

		getString(R.string.practise)   -> getDrawable(R.color.practise, R.color.practise_text)

		getString(R.string.seminar)    -> getDrawable(R.color.seminar, R.color.seminar_text)

		else                           -> getDrawable(R.color.exam, R.color.exam_text)
	}

	private fun getDrawable(backColorId: Int, textColorId: Int) =
		ColorBundle(
			background = ContextCompat.getColor(requireContext(), backColorId),
			text = ContextCompat.getColor(requireContext(), textColorId)
		)
}