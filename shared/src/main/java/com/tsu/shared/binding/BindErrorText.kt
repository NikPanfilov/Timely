package com.tsu.shared.binding

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

fun TextView.bindErrorText(
	scope: LifecycleCoroutineScope,
	flow: MutableStateFlow<String?>,
) {
	scope.launchWhenResumed {
		flow.onEach {
			visibility = if (it.isNullOrEmpty())
				View.VISIBLE
			else
				View.GONE
		}.collect()
	}
}