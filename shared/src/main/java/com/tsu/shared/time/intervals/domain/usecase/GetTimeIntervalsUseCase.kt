package com.tsu.shared.time.intervals.domain.usecase

import com.tsu.shared.time.intervals.domain.repository.TimeIntervalsRepository

class GetTimeIntervalsUseCase(
	private val repository: TimeIntervalsRepository,
) {

	suspend operator fun invoke() =
		repository.getTimeIntervals()
}