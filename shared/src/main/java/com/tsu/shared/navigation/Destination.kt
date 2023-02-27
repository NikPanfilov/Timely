package com.tsu.shared.navigation

import androidx.fragment.app.Fragment

interface FragmentDestination {

	fun createInstance(): Fragment
}