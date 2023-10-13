package com.tsu.weeklyschedule.ui

import com.tsu.weeklyschedule.databinding.FragmentWeeklyScheduleBinding
import com.tsu.weeklyschedule.presentation.WeeklyScheduleViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun FragmentWeeklyScheduleBinding.bindData(viewModel: WeeklyScheduleViewModel) {
	with(viewModel) {
		getWeekText(date)
		buttonPrevWeek.setOnClickListener {
			date = date.minusWeeks(1)
			getWeekText(date)
			getSchedule()
		}
		buttonNextWeek.setOnClickListener {
			date = date.plusWeeks(1)
			getWeekText(date)
			getSchedule()
		}

		toDailyButton.setOnClickListener { navigateToWeeklySchedule() }
		toStartButton.setOnClickListener { navigateToStart() }
	}
}

private fun FragmentWeeklyScheduleBinding.getWeekText(date: LocalDate) {
	val monday = date.with(DayOfWeek.MONDAY)
	val sunday = date.with(DayOfWeek.SUNDAY)
	val weekText = monday.format(DateTimeFormatter.ofPattern("dd.MM")) + " - " + sunday.format(DateTimeFormatter.ofPattern("dd.MM"))
	weekDates.text = weekText
}