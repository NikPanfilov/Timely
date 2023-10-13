package com.tsu.timely.di

import com.tsu.dailyschedule.presentation.DailyScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dailyScheduleModule = module {
	viewModel { (id: String, scheduleType: String) ->
		DailyScheduleViewModel(
			id = id,
			scheduleType = scheduleType,
			router = get(),
			getDailyScheduleUseCase = get(),
			bookAudienceUseCase = get(),
			getGroupsUseCase = get(),
			getAudiencesUseCase = get(),
		)
	}
}