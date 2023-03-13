package com.tsu.signin.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.tsu.shared.network.interceptors.TOKEN

class SharedPrefsDataStorage(context: Context) : UserDataStorage, TokenDataStorage {
	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val TYPE = "type"
		const val ID = "id"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun save(type: String, id: String) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(TYPE, type)
		e.putString(ID, id)
		e.apply()
	}

	override fun save(token: String) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(TOKEN, token)
		e.apply()
	}
}