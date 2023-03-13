package com.tsu.signup

import androidx.fragment.app.Fragment
import com.tsu.shared.navigation.FragmentDestination
import com.tsu.signup.ui.SignUpFragment

class SignUpDestination : FragmentDestination {

	override fun createInstance(): Fragment = SignUpFragment.newInstance()
}