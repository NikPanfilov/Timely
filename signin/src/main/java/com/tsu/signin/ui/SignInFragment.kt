package com.tsu.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.signin.databinding.FragmentSignInBinding
import com.tsu.signin.presentation.SignInSendState
import com.tsu.signin.presentation.SignInState
import com.tsu.signin.presentation.SignInViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

	companion object {

		fun newInstance() = SignInFragment()
	}

	private val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }

	private val viewModel by viewModel<SignInViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)
		viewModel.init()

		viewLifecycleOwner.lifecycleScope.launchWhenResumed {
			viewModel.stateFlow.onEach {
				renderUiState(it)
			}.collect()
		}

		return binding.root
	}

	private fun renderUiState(state: SignInState) {
		if (state is SignInState.Content)
			when (state.sendState) {
				is SignInSendState.Loading -> showToast("Loading")
				is SignInSendState.Error   -> showToast("Request error ${state.sendState.errorCode}")

				is SignInSendState.Success -> {
					showToast("Success")
					viewModel.navigateToWeeklySchedule()
				}

				else                       -> return
			}
	}

	private fun showToast(text: String) {
		Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
	}
}