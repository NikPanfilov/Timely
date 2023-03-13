package com.tsu.signin

import androidx.fragment.app.Fragment
import com.tsu.shared.navigation.FragmentDestination
import com.tsu.signin.ui.SignInFragment

class SignInDestination : FragmentDestination {

	override fun createInstance(): Fragment = SignInFragment.newInstance()
}