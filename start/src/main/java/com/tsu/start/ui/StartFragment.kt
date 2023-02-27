package com.tsu.start.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tsu.start.databinding.FragmentStartBinding
import com.tsu.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

	private val binding by lazy { FragmentStartBinding.inflate(layoutInflater) }

	private val viewModel by viewModel<StartViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		binding.bindData(viewModel)
		return binding.root
	}

	companion object {

		@JvmStatic
		fun newInstance() =
			StartFragment()
	}
}