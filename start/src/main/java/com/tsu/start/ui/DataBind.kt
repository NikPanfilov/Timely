package com.tsu.start.ui

import com.tsu.shared.ScheduleType
import com.tsu.shared.Type
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartViewModel

internal fun FragmentStartBinding.bindData(viewModel: StartViewModel) {
	with(viewModel) {
		signInButton.setOnClickListener { navigateToSignIn() }
		buttonGroups.setOnClickListener { navigateToScheduleSelection(ScheduleType(type = Type.Group)) }
		buttonTeachers.setOnClickListener { navigateToScheduleSelection(ScheduleType(type = Type.Teacher)) }
		buttonClassrooms.setOnClickListener { navigateToScheduleSelection(ScheduleType(type = Type.Classroom)) }
	}
}