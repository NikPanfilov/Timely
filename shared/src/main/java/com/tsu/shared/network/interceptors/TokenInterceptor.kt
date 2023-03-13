package com.tsu.shared.network.interceptors

import android.content.Context
import okhttp3.Interceptor

const val TOKEN = "Token"

fun tokenInterceptor(context: Context): Interceptor {
	return Interceptor { chain ->
		val sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
		val token = sharedPreferences.getString(TOKEN, "")

		val request = chain.request()
			.newBuilder()
			.addHeader("Authorization", "Bearer $token")
			.build()
		chain.proceed(request)
	}
}