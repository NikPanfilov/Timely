package com.tsu.signin.data.repository

import com.tsu.signin.data.storage.TokenDataStorage
import com.tsu.signin.domain.repository.TokenRepository

class TokenRepositoryImpl(
	private val storage: TokenDataStorage
) : TokenRepository {

	override fun save(token: String) = storage.save(token)

}