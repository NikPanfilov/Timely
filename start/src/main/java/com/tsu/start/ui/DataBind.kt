package com.tsu.start.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.AUDIENCES
import com.tsu.shared.GROUPS
import com.tsu.shared.PROFESSORS
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartViewModel

internal fun FragmentStartBinding.bindData(viewModel: StartViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		signInButton.setOnClickListener { navigateToSignIn() }
		signUpButton.setOnClickListener { navigateToSignUp() }

		buttonGroups.setOnClickListener { navigateToScheduleSelection(GROUPS) }
		buttonTeachers.setOnClickListener { navigateToScheduleSelection(PROFESSORS) }
		buttonClassrooms.setOnClickListener { navigateToScheduleSelection(AUDIENCES) }

		logoutButton.setOnClickListener {
			it.visibility = View.GONE
			signInButton.visibility = View.VISIBLE
			signUpButton.visibility = View.VISIBLE
			logout()
		}
	}
}