package com.tsu.dailyschedule.ui

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.dailyschedule.R
import com.tsu.dailyschedule.databinding.FragmentDailyScheduleBinding
import com.tsu.dailyschedule.presentation.DailyScheduleSendState
import com.tsu.dailyschedule.presentation.DailyScheduleState
import com.tsu.dailyschedule.presentation.DailyScheduleViewModel
import com.tsu.dailyschedule.ui.adapter.ScheduleAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun FragmentDailyScheduleBinding.bindData(viewModel: DailyScheduleViewModel, scope: LifecycleCoroutineScope, listAdapter: ScheduleAdapter) {
	scope.launchWhenResumed {
		viewModel.stateFlow.onEach {
			updateButtons(viewModel.date)
			when (viewModel.date.dayOfWeek) {
				DayOfWeek.MONDAY    -> setChosen(mondayButton)
				DayOfWeek.TUESDAY   -> setChosen(tuesdayButton)
				DayOfWeek.WEDNESDAY -> setChosen(wednesdayButton)
				DayOfWeek.THURSDAY  -> setChosen(thursdayButton)
				DayOfWeek.FRIDAY    -> setChosen(fridayButton)
				DayOfWeek.SATURDAY  -> setChosen(saturdayButton)
				DayOfWeek.SUNDAY    -> setChosen(sundayButton)
			}

			if (it is DailyScheduleState.Content && it.sendState is DailyScheduleSendState.Success) {
				Log.i("viewModel.date", viewModel.date.toString())
				if (viewModel.hasLessons()) {
					Log.i("debug", "has lessons")
					listAdapter.data = viewModel.schedule.value
					scheduleRecyclerView.visibility = View.VISIBLE
					noLessonImageView.visibility = View.GONE
				} else {
					Log.i("debug", "no lessons")
					noLessonImageView.visibility = View.VISIBLE
					scheduleRecyclerView.visibility = View.INVISIBLE
				}
			}
		}.collect()
	}
	with(viewModel) {
		toWeeklyButton.setOnClickListener { navigateToWeeklySchedule() }
		toStartButton.setOnClickListener { navigateToStart() }

		mondayButton.setOnClickListener {
			changeDate(DayOfWeek.MONDAY)
		}
		tuesdayButton.setOnClickListener {
			changeDate(DayOfWeek.TUESDAY)
		}
		wednesdayButton.setOnClickListener {
			changeDate(DayOfWeek.WEDNESDAY)
		}
		thursdayButton.setOnClickListener {
			changeDate(DayOfWeek.THURSDAY)
		}
		fridayButton.setOnClickListener {
			changeDate(DayOfWeek.FRIDAY)
		}
		saturdayButton.setOnClickListener {
			changeDate(DayOfWeek.SATURDAY)
		}
		sundayButton.setOnClickListener {
			changeDate(DayOfWeek.SUNDAY)
		}

		prevWeekButton.setOnClickListener {
			date = date.minusWeeks(1)
			getSchedule()
			updateButtons(date)
		}
		nextWeekButton.setOnClickListener {
			date = date.plusWeeks(1)
			getSchedule()
			updateButtons(date)
		}
	}
}

internal fun FragmentDailyScheduleBinding.updateButtons(date: LocalDate) {
	mondayButton.setDate(date, DayOfWeek.MONDAY)
	tuesdayButton.setDate(date, DayOfWeek.TUESDAY)
	wednesdayButton.setDate(date, DayOfWeek.WEDNESDAY)
	thursdayButton.setDate(date, DayOfWeek.THURSDAY)
	fridayButton.setDate(date, DayOfWeek.FRIDAY)
	saturdayButton.setDate(date, DayOfWeek.SATURDAY)
	sundayButton.setDate(date, DayOfWeek.SUNDAY)
}

private fun Button.setDate(date: LocalDate, dayOfWeek: DayOfWeek) {
	val dateForButton = date.with(dayOfWeek)
	text = dateForButton.format(DateTimeFormatter.ofPattern("dd.MM"))
}

private fun FragmentDailyScheduleBinding.setChosen(chosen: Button) {
	val white = ContextCompat.getColor(root.context, R.color.white)
	val green = ContextCompat.getColor(root.context, R.color.green_primary)
	for (id in daysGroup.referencedIds) {
		val button = linearLayout.findViewById<Button>(id)
		Log.i("wtf", id.toString())
		button.setTextColor(green)
		button.setBackgroundColor(white)
	}

	chosen.setTextColor(white)
	chosen.setBackgroundColor(green)
}