package com.tsu.shared.network.token.data.repository

import com.tsu.shared.network.token.data.storage.TokenDataStorage
import com.tsu.shared.network.token.domain.repository.TokenRepository

class TokenRepositoryImpl(
	private val storage: TokenDataStorage
) : TokenRepository {

	override fun save(token: String) = storage.save(token)

}