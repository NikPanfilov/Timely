package com.tsu.timely.di

import com.tsu.shared.network.createRetrofitService
import com.tsu.signup.data.api.RegistrationApi
import com.tsu.signup.data.datasource.RegistrationDataSource
import com.tsu.signup.data.datasource.RegistrationDataSourceImpl
import com.tsu.signup.data.repository.RegistrationRepositoryImpl
import com.tsu.signup.domain.repository.RegistrationRepository
import com.tsu.signup.domain.usecase.SignUpUseCase
import com.tsu.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
	viewModel {
		SignUpViewModel(router = get(), saveTokenUseCase = get(), signUpUseCase = get())
	}

	factory { createRetrofitService<RegistrationApi>(get()) }

	single<RegistrationDataSource> { RegistrationDataSourceImpl(get()) }

	factory<RegistrationRepository> { RegistrationRepositoryImpl(get()) }

	factory { SignUpUseCase(get()) }
}