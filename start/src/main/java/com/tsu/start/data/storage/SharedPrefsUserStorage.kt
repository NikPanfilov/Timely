package com.tsu.start.data.storage

import android.content.Context
import com.tsu.shared.network.interceptors.TOKEN
import com.tsu.start.data.dto.UserDataDto

class SharedPrefsUserStorage(context: Context) : UserDataStorage {
	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val TYPE = "type"
		const val ID = "id"
	}

	private val sharedPreferences =
		context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun loadData(): UserDataDto {
		return UserDataDto(
			type = sharedPreferences.getString(TYPE, null),
			id = sharedPreferences.getString(ID, null),
			token = sharedPreferences.getString(TOKEN, null)
		)
	}

	override fun clearData() {
		val editor = sharedPreferences.edit()
		editor.remove(TYPE)
		editor.remove(ID)
		editor.remove(TOKEN)
		editor.apply()
	}
}