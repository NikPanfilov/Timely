package com.tsu.start.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.CLASSROOMS
import com.tsu.shared.GROUPS
import com.tsu.shared.TEACHERS
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartViewModel

internal fun FragmentStartBinding.bindData(viewModel: StartViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		signInButton.setOnClickListener { navigateToSignIn() }
		signUpButton.setOnClickListener { navigateToSignUp() }

		buttonGroups.setOnClickListener { navigateToScheduleSelection(GROUPS) }
		buttonTeachers.setOnClickListener { navigateToScheduleSelection(TEACHERS) }
		buttonClassrooms.setOnClickListener { navigateToScheduleSelection(CLASSROOMS) }

		logoutButton.setOnClickListener {
			it.visibility = View.GONE
			signInButton.visibility = View.VISIBLE
			signUpButton.visibility = View.VISIBLE
			logout()
		}
	}
}