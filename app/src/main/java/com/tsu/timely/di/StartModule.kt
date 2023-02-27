package com.tsu.timely.di

import com.tsu.start.data.datasource.SharedPrefsUserStorage
import com.tsu.start.data.datasource.UserDataStorage
import com.tsu.start.data.repository.UserDataRepositoryImpl
import com.tsu.start.domain.repository.UserDataRepository
import com.tsu.start.domain.usecase.LoadUserDataUseCase
import com.tsu.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module {
	single { LoadUserDataUseCase(get()) }
	factory<UserDataRepository> { UserDataRepositoryImpl(get()) }
	factory<UserDataStorage> { SharedPrefsUserStorage(get()) }

	viewModel {
		StartViewModel(router = get(), loadUserData = get())
	}
}