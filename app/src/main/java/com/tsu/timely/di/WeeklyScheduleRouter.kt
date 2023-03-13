package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.weeklyschedule.data.api.WeeklyScheduleApi
import com.tsu.weeklyschedule.data.datasource.WeeklyScheduleDataSource
import com.tsu.weeklyschedule.data.datasource.WeeklyScheduleDataSourceImpl
import com.tsu.weeklyschedule.data.repository.WeeklyScheduleRepositoryImpl
import com.tsu.weeklyschedule.domain.repository.WeeklyScheduleRepository
import com.tsu.weeklyschedule.domain.usecase.GetWeeklyScheduleUseCase
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
			getTimeIntervalsUseCase = get()
		)
	}

	factory { createRetrofitService<WeeklyScheduleApi>(get()) }

	single<WeeklyScheduleDataSource> { WeeklyScheduleDataSourceImpl(get()) }

	factory<WeeklyScheduleRepository> { WeeklyScheduleRepositoryImpl(get()) }

	factory { GetWeeklyScheduleUseCase(get()) }
}