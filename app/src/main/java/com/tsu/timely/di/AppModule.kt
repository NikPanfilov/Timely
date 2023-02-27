package com.tsu.timely.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.tsu.shared.navigation.GlobalRouter
import com.tsu.timely.navigation.GlobalRouterImpl
import com.tsu.timely.navigation.GlobalRouterName.GLOBAL
import com.tsu.timely.presentation.MainViewModel
import com.tsu.timely.presentation.buildCicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
	single(named(GLOBAL)) { buildCicerone() }
	single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).router }
	single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).getNavigatorHolder() }
	single<GlobalRouter> { GlobalRouterImpl(get(named(GLOBAL))) }

	viewModel {
		MainViewModel(get())
	}
}