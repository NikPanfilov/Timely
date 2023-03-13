package com.tsu.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.signup.databinding.FragmentSignUpBinding
import com.tsu.signup.presentation.SignUpSendState
import com.tsu.signup.presentation.SignUpState
import com.tsu.signup.presentation.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

	companion object {

		fun newInstance() = SignUpFragment()
	}

	private val binding by lazy { FragmentSignUpBinding.inflate(layoutInflater) }

	private val viewModel by viewModel<SignUpViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		viewLifecycleOwner.lifecycleScope.launchWhenResumed {
			viewModel.stateFlow.onEach {
				renderUiState(it)
			}.collect()
		}

		return binding.root
	}

	private fun renderUiState(state: SignUpState) {
		if (state is SignUpState.Content)
			when (state.sendState) {
				is SignUpSendState.Loading -> showToast("Loading")
				is SignUpSendState.Error   -> showToast("Request error ${state.sendState.errorCode}")

				is SignUpSendState.Success -> {
					showToast("Success")
					viewModel.saveToken()
					viewModel.navigateToStart()
				}

				else                       -> return
			}
	}

	private fun showToast(text: String) {
		Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
	}
}