package com.tsu.signin.domain.repository

interface TokenRepository {

	fun save(token: String)
}