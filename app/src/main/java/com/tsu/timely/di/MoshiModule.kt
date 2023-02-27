package com.tsu.timely.di

import com.tsu.shared.network.provider.provideMoshi
import org.koin.dsl.module

val moshiModule = module {
	single { provideMoshi() }
}