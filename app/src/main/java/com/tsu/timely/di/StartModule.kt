package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.start.data.api.LogoutApi
import com.tsu.start.data.datasource.LogoutDataSource
import com.tsu.start.data.datasource.LogoutDataSourceImpl
import com.tsu.start.data.repository.LogoutRepositoryImpl
import com.tsu.start.data.repository.UserDataRepositoryImpl
import com.tsu.start.data.storage.SharedPrefsUserStorage
import com.tsu.start.data.storage.UserDataStorage
import com.tsu.start.domain.repository.LogoutRepository
import com.tsu.start.domain.repository.UserDataRepository
import com.tsu.start.domain.usecase.ClearUserDataUseCase
import com.tsu.start.domain.usecase.LoadUserDataUseCase
import com.tsu.start.domain.usecase.LogoutUseCase
import com.tsu.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module {
	single { LoadUserDataUseCase(get()) }
	factory<UserDataRepository> { UserDataRepositoryImpl(get()) }
	factory<UserDataStorage> { SharedPrefsUserStorage(get()) }

	factory { createRetrofitService<LogoutApi>(get()) }
	single<LogoutDataSource> { LogoutDataSourceImpl(get()) }
	factory<LogoutRepository> { LogoutRepositoryImpl(get()) }
	factory { LogoutUseCase(get()) }
	single { ClearUserDataUseCase(get()) }

	viewModel { (isFromApp: Boolean) ->
		StartViewModel(router = get(), loadUserData = get(), logoutUseCase = get(), clearUserDataUseCase = get(), isFromApp = isFromApp)
	}
}