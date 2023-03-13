package com.tsu.timely.di

import com.tsu.scheduleselection.data.api.SearchApi
import com.tsu.scheduleselection.data.datasource.SearchDataSource
import com.tsu.scheduleselection.data.datasource.SearchDataSourceImpl
import com.tsu.scheduleselection.data.repository.SearchRepositoryImpl
import com.tsu.scheduleselection.domain.repository.SearchRepository
import com.tsu.scheduleselection.domain.usecase.SearchUseCase
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.shared.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleSelectionModule = module {
	viewModel { (type: String) ->
		ScheduleSelectionViewModel(
			router = get(), scheduleType = type, searchUseCase = get()
		)
	}

	factory { createRetrofitService<SearchApi>(get()) }

	single<SearchDataSource> { SearchDataSourceImpl(get()) }

	factory<SearchRepository> { SearchRepositoryImpl(get()) }

	factory { SearchUseCase(get()) }
}