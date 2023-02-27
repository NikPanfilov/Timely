package com.tsu.shared.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import java.io.IOException

class NoConnectivityException : IOException()

fun noConnectionInterceptor(context: Context) = Interceptor { chain ->
	val checker = InternetCheck(context)
	return@Interceptor if (checker.isConnectionOff) {
		throw NoConnectivityException()
	} else {
		chain.proceed(chain.request())
	}
}

class InternetCheck(context: Context) {

	private val cm =
		context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

	val isConnectionOff: Boolean
		get() = !isConnectionOn()

	private fun isConnectionOn(): Boolean =
		postAndroidMInternetCheck()

	private fun postAndroidMInternetCheck(): Boolean =
		cm.getNetworkCapabilities(cm.activeNetwork)?.let {
			it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
		} ?: false
}