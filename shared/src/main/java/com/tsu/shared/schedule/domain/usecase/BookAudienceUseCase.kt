package com.tsu.shared.schedule.domain.usecase

import com.tsu.shared.schedule.domain.entity.Booking
import com.tsu.shared.schedule.domain.repository.BookRepository

class BookAudienceUseCase(private val repository: BookRepository) {

	suspend operator fun invoke(booking: Booking) {
		repository.book(booking)
	}
}