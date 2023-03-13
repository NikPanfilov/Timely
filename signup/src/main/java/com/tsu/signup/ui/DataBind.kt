package com.tsu.signup.ui

import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.binding.bindErrorText
import com.tsu.shared.binding.bindText
import com.tsu.signup.databinding.FragmentSignUpBinding
import com.tsu.signup.presentation.SignUpViewModel

internal fun FragmentSignUpBinding.bindData(viewModel: SignUpViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		fullNameTextEmail.bindText(scope, fullNameMutableFlow)
		textViewFullNameError.bindErrorText(scope, fullNameMutableFlow)

		editTextEmail.bindText(scope, emailMutableFlow)
		textViewEmailError.bindErrorText(scope, emailMutableFlow)

		editTextTextPassword.bindText(scope, passwordMutableFlow)
		textViewPasswordError.bindErrorText(scope, passwordMutableFlow)

		buttonSignUp.setOnClickListener { signUp() }
	}
}