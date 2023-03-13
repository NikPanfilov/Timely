package com.tsu.start.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.CLASSROOMS
import com.tsu.shared.GROUPS
import com.tsu.shared.TEACHERS
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartSendState
import com.tsu.start.presentation.StartState
import com.tsu.start.presentation.StartViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.math.log

internal fun FragmentStartBinding.bindData(viewModel: StartViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		signInButton.setOnClickListener { navigateToSignIn() }
		buttonGroups.setOnClickListener { navigateToScheduleSelection(GROUPS) }
		buttonTeachers.setOnClickListener { navigateToScheduleSelection(TEACHERS) }
		buttonClassrooms.setOnClickListener { navigateToScheduleSelection(CLASSROOMS) }

		logoutButton.setOnClickListener {
			it.visibility = View.GONE
			signInButton.visibility = View.VISIBLE
			logout()
		}
	}
}