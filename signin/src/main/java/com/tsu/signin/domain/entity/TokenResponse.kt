package com.tsu.signin.domain.entity

data class TokenResponse(val token: String?, val email: String?, val role: List<String?>)
