package com.tsu.timely.di

import com.tsu.scheduleselection.presentation.ScheduleSelectionRouter
import com.tsu.signin.presentation.SignInRouter
import com.tsu.start.presentation.StartRouter
import com.tsu.timely.navigation.MainActivityRouterImpl
import com.tsu.timely.navigation.ScheduleSelectionRouterImpl
import com.tsu.timely.navigation.SignInRouterImpl
import com.tsu.timely.navigation.StartRouterImpl
import com.tsu.timely.presentation.MainActivityRouter
import org.koin.dsl.module

val routerModule = module {
	factory<MainActivityRouter> { MainActivityRouterImpl(get()) }
	factory<StartRouter> { StartRouterImpl(get()) }
	factory<SignInRouter> { SignInRouterImpl(get()) }
	factory<ScheduleSelectionRouter> { ScheduleSelectionRouterImpl(get()) }
}