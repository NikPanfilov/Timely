package com.tsu.shared.network.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(
	logging: Interceptor,
	noConnection: Interceptor,
	token: Interceptor
): OkHttpClient =
	OkHttpClient().newBuilder()
		.addInterceptor(token)
		.addInterceptor(noConnection)
		.addInterceptor(logging)
		.applyDefaultSetups()
		.build()

private const val VALUE_TIMEOUT: Long = 60

private fun OkHttpClient.Builder.applyDefaultSetups(): OkHttpClient.Builder {
	connectTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	writeTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	readTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	hostnameVerifier { _, _ -> true }
	return this
}
