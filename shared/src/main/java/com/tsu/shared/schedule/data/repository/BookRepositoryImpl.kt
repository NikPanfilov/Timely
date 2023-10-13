package com.tsu.shared.schedule.data.repository

import com.tsu.shared.schedule.data.api.BookApi
import com.tsu.shared.schedule.data.mapper.toDto
import com.tsu.shared.schedule.domain.entity.Booking
import com.tsu.shared.schedule.domain.repository.BookRepository

class BookRepositoryImpl(private val api: BookApi) : BookRepository {

	override suspend fun book(booking: Booking) {
		api.book(booking.toDto())
	}
}