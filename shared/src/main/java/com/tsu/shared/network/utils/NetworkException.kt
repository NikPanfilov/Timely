package com.tsu.shared.network.utils

object NetworkCode {

	const val INTERNET_CONNECTION_ERROR = 0 // допустим
	const val UNKNOWN = -1
	const val UNAUTHORIZED = 401
	const val NOT_FOUND = 404
	const val OK = 200
	const val FAIL = 400
	const val FORBIDDEN = 403
	const val CONFLICT = 409
}

data class NetworkException(
	val message: String?,
	val code: Int
)