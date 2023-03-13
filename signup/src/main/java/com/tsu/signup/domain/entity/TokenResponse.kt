package com.tsu.signup.domain.entity

data class TokenResponse(val token: String?, val email: String?, val role: List<String?>, val group: Group?, val teacher: Teacher?)

data class Group(val name: String, val id: String?)

data class Teacher(val name: String, val id: String?)
