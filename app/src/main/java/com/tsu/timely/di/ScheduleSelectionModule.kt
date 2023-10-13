package com.tsu.timely.di

import com.tsu.scheduleselection.data.api.SearchApi
import com.tsu.scheduleselection.data.repository.SearchRepositoryImpl
import com.tsu.scheduleselection.domain.repository.SearchRepository
import com.tsu.scheduleselection.domain.usecase.GetAudiencesUseCase
import com.tsu.scheduleselection.domain.usecase.GetGroupsUseCase
import com.tsu.scheduleselection.domain.usecase.GetProfessorsUseCase
import com.tsu.scheduleselection.presentation.ScheduleSelectionViewModel
import com.tsu.shared.network.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleSelectionModule = module {
	viewModel { (type: String) ->
		ScheduleSelectionViewModel(
			router = get(), scheduleType = type, getAudiencesUseCase = get(), getGroupsUseCase = get(), getProfessorsUseCase = get()
		)
	}

	factory { createRetrofitService<SearchApi>(get()) }

	factory<SearchRepository> { SearchRepositoryImpl(get()) }

	factory { GetAudiencesUseCase(get()) }
	factory { GetGroupsUseCase(get()) }
	factory { GetProfessorsUseCase(get()) }
}