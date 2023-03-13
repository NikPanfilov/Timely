package com.tsu.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tsu.signup.databinding.FragmentSignUpBinding
import com.tsu.signup.presentation.SignUpViewModel
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

		return binding.root
	}
}