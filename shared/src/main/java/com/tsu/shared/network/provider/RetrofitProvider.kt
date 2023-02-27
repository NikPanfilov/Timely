package com.tsu.shared.network.provider

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideRetrofit(
	okHttpClient: OkHttpClient,
	moshi: Moshi,
	url: String
): Retrofit = Retrofit.Builder()
	.addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
	.client(okHttpClient)
	.baseUrl(url)
	.build()