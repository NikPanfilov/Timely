package com.tsu.timely.di

import com.tsu.weeklyschedule.presentation.WeeklyScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.time.LocalDate

val weeklyScheduleModule = module {
	viewModel { (id: String, scheduleType: String, date: LocalDate) ->
		WeeklyScheduleViewModel(
			id = id,
			scheduleType = scheduleType,
			currentDate = date,
			router = get(),
			getWeeklyScheduleUseCase = get(),
			getGroupsUseCase = get(),
			getAudiencesUseCase = get(),
			bookAudienceUseCase = get()
		)
	}
}