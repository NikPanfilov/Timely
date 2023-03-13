package com.tsu.weeklyschedule.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.shared.entity.Lesson
import com.tsu.shared.time.intervals.domain.entity.TimeInterval
import com.tsu.weeklyschedule.R
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)
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

	private fun drawTable(schedule: List<List<Lesson?>>) {

		binding.tableLayout.addView(renderDateRow())

		for (i in viewModel.intervals.indices) {
			val tableRow = TableRow(context)

			tableRow.addView(renderIntervalCell(viewModel.intervals[i]))

			for (lesson in schedule[i]) {
				if (lesson != null)
					tableRow.addView(renderCell(lesson))
				else
					tableRow.addView(EmptyItemBinding.inflate(layoutInflater).root)
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

	private fun renderIntervalCell(interval: TimeInterval): View {
		val cell = IntervalItemBinding.inflate(layoutInflater)
		cell.startTimeTextView.text = interval.startTime
		cell.endTimeTextView.text = interval.endTime

		return cell.root
	}

	private fun renderCell(lesson: Lesson): View {
		val cell = WeeklyLessonItemBinding.inflate(layoutInflater)
		cell.subjectTextView.text = lesson.name.name
		cell.classroomTextView.text = lesson.classroom.name

		val builder = StringBuilder()
		lesson.group.forEach { builder.append(it.name + "\n") }
		builder.dropLast(1)
		cell.groupTextView.text = builder

		when (lesson.tag.name) {
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

}