package com.tsu.details

import com.tsu.details.ui.DetailsFragment
import com.tsu.shared.navigation.FragmentDestination
import com.tsu.shared.navigation.holder.LessonHolder

class DetailsDestination(
	private val lesson: LessonHolder,
) : FragmentDestination {

	override fun createInstance() = DetailsFragment.newInstance(lesson)
}