package com.tsu.timely.di

import com.tsu.dailyschedule.data.api.DailyScheduleApi
import com.tsu.dailyschedule.data.datasource.DailyScheduleDataSource
import com.tsu.dailyschedule.data.datasource.DailyScheduleDataSourceImpl
import com.tsu.dailyschedule.data.repository.DailyScheduleRepositoryImpl
import com.tsu.dailyschedule.domain.repository.DailyScheduleRepository
import com.tsu.dailyschedule.domain.usecase.GetDailyScheduleUseCase
import com.tsu.dailyschedule.presentation.DailyScheduleViewModel
import com.tsu.shared.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dailyScheduleModule = module {
	viewModel { (id: String, scheduleType: String) ->
		DailyScheduleViewModel(
			id = id,
			scheduleType = scheduleType,
			router = get(),
			getDailyScheduleUseCase = get(),
			getTimeIntervalsUseCase = get()
		)
	}

	factory { createRetrofitService<DailyScheduleApi>(get()) }

	single<DailyScheduleDataSource> { DailyScheduleDataSourceImpl(get()) }

	factory<DailyScheduleRepository> { DailyScheduleRepositoryImpl(get()) }

	factory { GetDailyScheduleUseCase(get()) }
}