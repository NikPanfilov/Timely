package com.tsu.shared.network.provider

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(
	interceptors: List<Interceptor> = emptyList(),
	authenticators: List<Authenticator> = emptyList(),
): OkHttpClient =
	OkHttpClient().newBuilder()
		.applyDefaultSetups(interceptors, authenticators)
		.build()

private const val VALUE_TIMEOUT: Long = 60

private fun OkHttpClient.Builder.applyDefaultSetups(
	interceptors: List<Interceptor>,
	authenticators: List<Authenticator>,
): OkHttpClient.Builder {
	connectTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	writeTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	readTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	interceptors.forEach { addInterceptor(it) }
	authenticators.forEach { authenticator(it) }
	hostnameVerifier { _, _ -> true }
	return this
}
