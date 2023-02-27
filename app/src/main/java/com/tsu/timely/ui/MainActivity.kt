package com.tsu.timely.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.tsu.timely.R
import com.tsu.timely.navigation.GlobalRouterName.GLOBAL
import com.tsu.timely.presentation.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

	private val navigatorHolder: NavigatorHolder by inject(named(GLOBAL))
	private val navigator = AppNavigator(this, R.id.host_global)

	private val viewModel by viewModel<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.host)
	}

	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
		viewModel.openStartScreen()
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}
}