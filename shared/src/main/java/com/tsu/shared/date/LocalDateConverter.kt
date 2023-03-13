package com.tsu.shared.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.toFormattedString(): String {
	val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
	return this.format(formatter)
}

fun String.toLocalDate(): LocalDate = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")).toLocalDate()