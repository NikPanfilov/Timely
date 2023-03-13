package com.tsu.signin.domain.repository

interface UserDataRepository {

	fun save(type: String, id: String)
}