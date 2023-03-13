package com.tsu.start.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class StartFragment : Fragment() {

	private val binding by lazy { FragmentStartBinding.inflate(layoutInflater) }

	private val viewModel: StartViewModel by viewModel {
		parametersOf(
			arguments?.get(IS_FROM_APP)
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		binding.bindData(viewModel, lifecycleScope)
		if (viewModel.isUserLogged()) {
			binding.logoutButton.visibility = View.VISIBLE
			binding.signInButton.visibility = View.INVISIBLE
			binding.signUpButton.visibility = View.INVISIBLE
		} else {
			binding.logoutButton.visibility = View.GONE
			binding.signInButton.visibility = View.VISIBLE
			binding.signUpButton.visibility = View.VISIBLE
		}

		return binding.root
	}

	companion object {

		private const val IS_FROM_APP = "isFromApp"

		@JvmStatic
		fun newInstance(isFromApp: Boolean) =
			StartFragment().apply {
				arguments = bundleOf(IS_FROM_APP to isFromApp)
			}
	}
}