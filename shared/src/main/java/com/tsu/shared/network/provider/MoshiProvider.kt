package com.tsu.shared.network.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

fun provideMoshi(): Moshi = Moshi.Builder()
	.add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
	.add(KotlinJsonAdapterFactory())
	.build()