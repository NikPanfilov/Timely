package com.tsu.timely.di

import com.tsu.shared.network.interceptors.loggingInterceptor
import com.tsu.shared.network.interceptors.noConnectionInterceptor
import com.tsu.shared.network.provider.provideOkHttpClient
import com.tsu.shared.network.provider.provideRetrofit
import com.tsu.timely.App.Companion.BACKEND
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOG_INTERCEPTOR = "logInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"

val networkModule = module {
	single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
	single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }

	single {
		provideOkHttpClient(
			interceptors = listOf(
				get(named(LOG_INTERCEPTOR)),
				get(named(NO_CONNECT_INTERCEPTOR))
			)
		)
	}
	single { provideRetrofit(okHttpClient = get(), moshi = get(), url = getProperty(BACKEND)) }
}