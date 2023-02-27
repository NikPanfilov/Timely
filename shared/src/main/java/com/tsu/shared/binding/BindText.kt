package com.tsu.shared.binding

import android.os.Looper
import android.widget.EditText
import androidx.lifecycle.LifecycleCoroutineScope
import com.tsu.shared.flow.asFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach

fun EditText.bindText(
	scope: LifecycleCoroutineScope,
	flow: MutableStateFlow<String?>,
) {
	check(Looper.myLooper() == Looper.getMainLooper()) {
		"flow binding run in ${Thread.currentThread().name}, but must be in main"
	}

	scope.launchWhenResumed {
		flow.filter {
			text?.toString() != it
		}.onEach {
			setText(it)
		}
	}

	scope.launchWhenResumed {
		asFlow().filter {
			it != flow.value
		}.onEach {
			flow.value = it
		}.collect()
	}
}