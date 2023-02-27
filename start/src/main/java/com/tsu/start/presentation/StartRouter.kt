package com.tsu.start.presentation

import com.tsu.shared.ScheduleType

interface StartRouter {

	fun navigateToSignIn()
	fun navigateToScheduleSelection(type: ScheduleType)
	fun navigateToSchedule()
}