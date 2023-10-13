package com.tsu.dailyschedule.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsu.dailyschedule.R
import com.tsu.dailyschedule.databinding.BookDialogBinding
import com.tsu.dailyschedule.databinding.FragmentDailyScheduleBinding
import com.tsu.dailyschedule.presentation.DailyScheduleViewModel
import com.tsu.dailyschedule.ui.adapter.ScheduleAdapter
import com.tsu.shared.AUDIENCES
import com.tsu.shared.GROUPS
import com.tsu.shared.entity.TimeSlot
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

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewModel.init()

		val gestureDetector = GestureDetectorCompat(requireContext(), GestureListener(viewModel))
		adapter = ScheduleAdapter(this::clickListItem, this::openDialog, this::getColors, gestureDetector)

		binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(this.context)
		binding.scheduleRecyclerView.adapter = adapter

		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope, adapter)
		binding.updateButtons(viewModel.date)
		binding.noLessonImageView.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }

		return binding.root
	}

	private fun clickListItem(lesson: TimeSlot.Lesson) {
		viewModel.navigateToDetails(lesson)
	}

	private fun openDialog(timeSlot: Int) {
		val dialogBinding = BookDialogBinding.inflate(layoutInflater)
		val dialog = AlertDialog.Builder(requireContext()).setView(dialogBinding.root).create()
		dialogBinding.bindAudiences(timeSlot)
		dialogBinding.bindGroups()
		dialog.show()

		dialogBinding.okButton.setOnClickListener {
			viewModel.bookAudience(
				audienceName = dialogBinding.spinnerAudiences.text.toString(),
				groupsId = selectedGroups,
				timeSlot = timeSlot,
				description = dialogBinding.titleEditText.text.toString()
			)
			dialog.dismiss()
		}
	}

	private fun BookDialogBinding.bindAudiences(timeSlot: Int) {
		val audiences = viewModel.getFreeAudiences(timeSlot)
		val adapter = ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, audiences.map { it.name })
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spinnerAudiences.setAdapter(adapter)

		if (arguments?.get(TYPE) == AUDIENCES)
			audiences.find { it.id == arguments?.get(ID) }?.let { spinnerAudiences.setText(it.name, false) }
	}

	private val selectedGroups = mutableListOf<String>()
	private fun BookDialogBinding.bindGroups() {
		val groups = viewModel.groups.value
		val adapter = ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, groups.map { it.name })
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		spinnerGroups.setAdapter(adapter)
		spinnerGroups.setOnItemClickListener { parent, view, position, l ->
			val selectedItem = parent.getItemAtPosition(position).toString()
			if (selectedGroups.contains(selectedItem))
				selectedGroups.remove(selectedItem)
			else
				selectedGroups.add(selectedItem)
		}

		if (arguments?.get(TYPE) == GROUPS)
			groups.find { it.id == arguments?.get(ID) }?.let {
				spinnerGroups.setText(it.name, false)
				selectedGroups.add(it.name)
			}
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