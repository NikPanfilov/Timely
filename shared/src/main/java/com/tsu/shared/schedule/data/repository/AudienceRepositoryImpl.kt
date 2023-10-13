package com.tsu.shared.schedule.data.repository

import com.tsu.shared.date.toFormattedString
import com.tsu.shared.entity.Audience
import com.tsu.shared.schedule.data.api.AudienceApi
import com.tsu.shared.schedule.data.mapper.toEntity
import com.tsu.shared.schedule.domain.repository.AudienceRepository
import java.time.LocalDate

class AudienceRepositoryImpl(private val api: AudienceApi) : AudienceRepository {

	override suspend fun getAudiences(date: LocalDate, timeSlot: Int): List<Audience> =
		api.getAudiences(date.toFormattedString(), timeSlot).map { it.toEntity() }
}