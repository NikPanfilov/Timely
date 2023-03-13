package com.tsu.signin.data.storage

interface UserDataStorage {

	fun save(type: String, id: String)
}