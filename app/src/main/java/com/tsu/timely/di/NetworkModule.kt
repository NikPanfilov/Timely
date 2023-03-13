package com.tsu.timely.di

import com.tsu.shared.network.interceptors.loggingInterceptor
import com.tsu.shared.network.interceptors.noConnectionInterceptor
import com.tsu.shared.network.interceptors.tokenInterceptor
import com.tsu.shared.network.provider.provideOkHttpClient
import com.tsu.shared.network.provider.provideRetrofit
import com.tsu.shared.network.token.data.repository.TokenRepositoryImpl
import com.tsu.shared.network.token.data.storage.TokenDataStorage
import com.tsu.shared.network.token.domain.repository.TokenRepository
import com.tsu.shared.network.token.domain.usecase.SaveTokenUseCase
import com.tsu.signin.data.storage.SharedPrefsDataStorage
import com.tsu.timely.App.Companion.BACKEND
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOG_INTERCEPTOR = "logInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"
const val TOKEN_INTERCEPTOR = "tokenInterceptor"

val networkModule = module {
	single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
	single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }
	single(named(TOKEN_INTERCEPTOR)) { tokenInterceptor(androidContext()) }

	single {
		provideOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			token = get(named(TOKEN_INTERCEPTOR))
		)
	}
	single { provideRetrofit(okHttpClient = get(), moshi = get(), url = getProperty(BACKEND)) }

	factory<TokenDataStorage> { SharedPrefsDataStorage(get()) }
	factory<TokenRepository> { TokenRepositoryImpl(get()) }
	factory { SaveTokenUseCase(get()) }
}