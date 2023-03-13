package com.tsu.shared.network.token.domain.repository

interface TokenRepository {

	fun save(token: String)
}