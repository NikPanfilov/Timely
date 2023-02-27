package com.tsu.shared.network.interceptors

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

fun loggingInterceptor(): Interceptor =
	HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)