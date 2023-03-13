package com.tsu.signin.data.storage

interface TokenDataStorage {

	fun save(token: String)
}