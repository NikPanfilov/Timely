package com.tsu.shared.network

import android.util.Log
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import retrofit2.Retrofit

inline fun <reified T> createRetrofitService(retrofit: Retrofit): T =
	retrofit.create(T::class.java)

inline fun <reified T> Scope.getRetrofit(attachedValue: String? = null): T =
	get(T::class, named(attachedValue ?: "DEBUG").also { Log.d("getRetrofit", it.value) })