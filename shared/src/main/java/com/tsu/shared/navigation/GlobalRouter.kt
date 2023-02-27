package com.tsu.shared.navigation

interface GlobalRouter {

	fun open(destination: FragmentDestination)

	fun replace(fragmentDestination: FragmentDestination)

	fun newRoot(fragmentDestination: FragmentDestination)

	fun popToRoot()

	fun exit()

	fun finish()

	fun popTo(fragmentDestinationClass: Class<out FragmentDestination>)
}