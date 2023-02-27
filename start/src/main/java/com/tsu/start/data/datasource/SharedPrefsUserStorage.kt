package com.tsu.start.data.datasource

import android.content.Context

class SharedPrefsUserStorage(context: Context) : UserDataStorage {
	companion object {

		const val SHARED_PREFERENCES_FILENAME = "userData"
		const val TOKEN = "token"
		const val TYPE = "type"
		const val NAME = "name"
	}

	private val sharedPreferences =
		context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun loadData(): UserData {
		return UserData(
			token = sharedPreferences.getString(TOKEN, "") ?: "",
			type = sharedPreferences.getString(TYPE, "") ?: "",
			name = sharedPreferences.getString(NAME, "") ?: ""
		)
	}
}