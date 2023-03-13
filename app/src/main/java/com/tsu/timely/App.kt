package com.tsu.timely

import android.app.Application
import com.tsu.timely.di.appModule
import com.tsu.timely.di.dailyScheduleModule
import com.tsu.timely.di.detailsModule
import com.tsu.timely.di.moshiModule
import com.tsu.timely.di.networkModule
import com.tsu.timely.di.routerModule
import com.tsu.timely.di.scheduleSelectionModule
import com.tsu.timely.di.signInModule
import com.tsu.timely.di.startModule
import com.tsu.timely.di.timeIntervalsModule
import com.tsu.timely.di.weeklyScheduleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	companion object {

		const val BACKEND = "BACKEND"
		private const val BACKEND_ENDPOINT = "https://timely.markridge.space/"

		internal lateinit var INSTANCE: App
			private set
	}

	override fun onCreate() {
		super.onCreate()

		INSTANCE = this

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			properties(mapOf("BACKEND" to BACKEND_ENDPOINT))
			androidFileProperties()

			modules(appModule)
			modules(routerModule)
			modules(networkModule)
			modules(moshiModule)
			modules(timeIntervalsModule)

			modules(signInModule)
			modules(startModule)
			modules(scheduleSelectionModule)
			modules(dailyScheduleModule)
			modules(weeklyScheduleModule)
			modules(detailsModule)
		}
	}
}