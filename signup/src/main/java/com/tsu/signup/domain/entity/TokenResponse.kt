package com.tsu.signup.domain.entity

data class TokenResponse(val token: String?, val email: String?, val role: List<String?>, val group: String, val teacher: String)
