package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.signin.data.api.LoginApi
import com.tsu.signin.data.datasource.LoginDataSource
import com.tsu.signin.data.datasource.LoginDataSourceImpl
import com.tsu.signin.data.repository.LoginRepositoryImpl
import com.tsu.signin.data.repository.UserDataRepositoryImpl
import com.tsu.signin.data.storage.SharedPrefsDataStorage
import com.tsu.signin.data.storage.UserDataStorage
import com.tsu.signin.domain.repository.LoginRepository
import com.tsu.signin.domain.repository.UserDataRepository
import com.tsu.signin.domain.usecase.LoginUseCase
import com.tsu.signin.domain.usecase.SaveUserDataUseCase
import com.tsu.signin.presentation.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInModule = module {
	viewModel {
		SignInViewModel(router = get(), loginUseCase = get(), saveUserDataUseCase = get(), saveTokenUseCase = get())
	}

	factory { createRetrofitService<LoginApi>(get()) }
	factory<UserDataStorage> { SharedPrefsDataStorage(get()) }

	single<LoginDataSource> { LoginDataSourceImpl(get()) }

	factory<LoginRepository> { LoginRepositoryImpl(get()) }
	factory<UserDataRepository> { UserDataRepositoryImpl(get()) }

	factory { LoginUseCase(get()) }
	factory { SaveUserDataUseCase(get()) }
}