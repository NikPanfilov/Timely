package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.shared.time.intervals.data.api.TimeIntervalsApi
import com.tsu.shared.time.intervals.data.datasource.TimeIntervalsDataSource
import com.tsu.shared.time.intervals.data.datasource.TimeIntervalsDataSourceImpl
import com.tsu.shared.time.intervals.data.repository.TimeIntervalsRepositoryImpl
import com.tsu.shared.time.intervals.domain.repository.TimeIntervalsRepository
import com.tsu.shared.time.intervals.domain.usecase.GetTimeIntervalsUseCase
import org.koin.dsl.module

val timeIntervalsModule = module {
	factory { createRetrofitService<TimeIntervalsApi>(get()) }

	single<TimeIntervalsDataSource> { TimeIntervalsDataSourceImpl(get()) }

	factory<TimeIntervalsRepository> { TimeIntervalsRepositoryImpl(get()) }

	factory { GetTimeIntervalsUseCase(get()) }
}