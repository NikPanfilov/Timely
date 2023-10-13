package com.tsu.weeklyschedule.ui

import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableRow
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.shared.AUDIENCES
import com.tsu.shared.GROUPS
import com.tsu.shared.entity.TimeSlot
import com.tsu.weeklyschedule.R
import com.tsu.weeklyschedule.databinding.BookDialogBinding
import com.tsu.weeklyschedule.databinding.BookedItemBinding
import com.tsu.weeklyschedule.databinding.DateItemBinding
import com.tsu.weeklyschedule.databinding.EmptyItemBinding
import com.tsu.weeklyschedule.databinding.FragmentWeeklyScheduleBinding
import com.tsu.weeklyschedule.databinding.IntervalItemBinding
import com.tsu.weeklyschedule.databinding.WeeklyLessonItemBinding
import com.tsu.weeklyschedule.presentation.WeeklyScheduleSendState
import com.tsu.weeklyschedule.presentation.WeeklyScheduleState
import com.tsu.weeklyschedule.presentation.WeeklyScheduleViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeeklyScheduleFragment : Fragment() {

	companion object {

		private const val ID = "id"
		private const val TYPE = "type"
		private const val DATE = "date"

		fun newInstance(id: String, type: String, date: LocalDate) = WeeklyScheduleFragment().apply {
			arguments = bundleOf(ID to id, TYPE to type, DATE to date)
		}
	}

	private val binding by lazy { FragmentWeeklyScheduleBinding.inflate(layoutInflater) }

	private val viewModel: WeeklyScheduleViewModel by viewModel {
		parametersOf(
			arguments?.get(ID), arguments?.get(TYPE), arguments?.get(DATE)
		)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel)
		viewModel.init()
		viewModel.getSchedule()

		viewLifecycleOwner.lifecycleScope.launchWhenResumed {
			viewModel.stateFlow.onEach {
				if (it is WeeklyScheduleState.Content)
					if (it.sendState is WeeklyScheduleSendState.Success) {
						binding.tableLayout.removeAllViews()
						drawTable(viewModel.renderSchedule())
					}
			}.collect()
		}

		return binding.root
	}

	private fun drawTable(schedule: List<List<TimeSlot>>) {
		binding.tableLayout.addView(renderDateRow())

		for (i in 0..6) {
			val tableRow = TableRow(context)

			tableRow.addView(renderIntervalCell(schedule[i][0]))

			for (lesson in schedule[i]) {
				if (lesson is TimeSlot.Lesson)
					tableRow.addView(renderCell(lesson))
				else if (lesson is TimeSlot.Break)
					tableRow.addView(renderCell(lesson))
				else if (lesson is TimeSlot.Booked)
					tableRow.addView(renderCell(lesson))
			}
			binding.tableLayout.addView(tableRow)
		}
	}

	private fun renderDateRow(): TableRow {
		val row = TableRow(context)
		row.addView(View(context))
		var currentDate = viewModel.date.with(DayOfWeek.MONDAY)
		for (i in 0..6) {
			val dateView = DateItemBinding.inflate(layoutInflater)
			dateView.dateTextView.text = currentDate.format(DateTimeFormatter.ofPattern("dd.MM"))
			row.addView(dateView.root)

			currentDate = currentDate.plusDays(1)
		}

		return row
	}

	private fun renderIntervalCell(timeSlot: TimeSlot): View {
		val cell = IntervalItemBinding.inflate(layoutInflater)
		cell.startTimeTextView.text = timeSlot.starts.toString()
		cell.endTimeTextView.text = timeSlot.ends.toString()

		return cell.root
	}

	private fun renderCell(lesson: TimeSlot.Lesson): View {
		val cell = WeeklyLessonItemBinding.inflate(layoutInflater)
		cell.subjectTextView.text = lesson.title
		cell.classroomTextView.text = lesson.audience.name

		val builder = StringBuilder()
		lesson.groups.forEach { builder.append(it.name + "\n") }
		builder.dropLast(1)
		cell.groupTextView.text = builder

		when (lesson.type) {
			getString(R.string.laboratory) -> cell.setCellColor(R.color.laboratory, R.color.laboratory_text)

			getString(R.string.lecture)    -> cell.setCellColor(R.color.lecture, R.color.lecture_text)

			getString(R.string.practise)   -> cell.setCellColor(R.color.practise, R.color.practise_text)

			getString(R.string.seminar)    -> cell.setCellColor(R.color.seminar, R.color.seminar_text)

			else                           -> cell.setCellColor(R.color.exam, R.color.exam_text)
		}

		cell.root.setOnClickListener { viewModel.navigateToDetails(lesson) }

		return cell.root
	}

	private fun WeeklyLessonItemBinding.setCellColor(backColorId: Int, textColorId: Int) {
		(view.background as GradientDrawable).setColor(getColor(backColorId))
		subjectTextView.setTextColor(getColor(textColorId))
		classroomTextView.setTextColor(getColor(textColorId))
		groupTextView.setTextColor(getColor(textColorId))
	}

	private fun getColor(id: Int) =
		ContextCompat.getColor(requireContext(), id)

	private fun renderCell(breakSlot: TimeSlot.Break): View {
		val cell = EmptyItemBinding.inflate(layoutInflater)
		cell.bookButton.setOnClickListener {
			openDialog(breakSlot.date, breakSlot.timeSlot)
		}

		return cell.root
	}

	private fun renderCell(booked: TimeSlot.Booked): View {
		val cell = BookedItemBinding.inflate(layoutInflater)
		cell.bookDescriptionTextView.text = booked.description
		cell.classroomTextView.text = booked.audience.name

		val builder = StringBuilder()
		booked.groups.forEach { builder.append(it.name + "\n") }
		builder.dropLast(1)
		cell.groupTextView.text = builder

		return cell.root
	}

	private fun openDialog(date: LocalDate, timeSlot: Int) {
		val dialogBinding = BookDialogBinding.inflate(layoutInflater)
		val dialog = AlertDialog.Builder(requireContext()).setView(dialogBinding.root).create()
		dialogBinding.bindAudiences(date, timeSlot)
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

	private fun BookDialogBinding.bindAudiences(date: LocalDate, timeSlot: Int) {
		val audiences = viewModel.getFreeAudiences(date, timeSlot)
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
}