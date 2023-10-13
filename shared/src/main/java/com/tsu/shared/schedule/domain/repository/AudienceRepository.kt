package com.tsu.shared.schedule.domain.repository

import com.tsu.shared.entity.Audience
import java.time.LocalDate

interface AudienceRepository {

	suspend fun getAudiences(date: LocalDate, timeSlot: Int): List<Audience>
}