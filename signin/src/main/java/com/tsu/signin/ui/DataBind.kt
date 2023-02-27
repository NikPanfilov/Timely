package com.tsu.signin.ui

import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.binding.bindErrorText
import com.tsu.shared.binding.bindText
import com.tsu.signin.databinding.FragmentSignInBinding
import com.tsu.signin.presentation.SignInViewModel

internal fun FragmentSignInBinding.bindData(viewModel: SignInViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		editTextEmail.bindText(scope, emailMutableFlow)
		textViewEmailError.bindErrorText(scope, emailMutableFlow)

		editTextTextPassword.bindText(scope, passwordMutableFlow)
		textViewPasswordError.bindErrorText(scope, passwordMutableFlow)

		buttonSignIn.setOnClickListener { signIn() }
	}

}