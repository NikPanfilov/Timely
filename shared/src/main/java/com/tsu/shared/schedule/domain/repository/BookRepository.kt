package com.tsu.shared.schedule.domain.repository

import com.tsu.shared.schedule.domain.entity.Booking

interface BookRepository {

	suspend fun book(booking: Booking)
}