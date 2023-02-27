package com.tsu.shared.flow

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/***
 * Отслеживает изменения текста в EditText
 */
fun EditText.asFlow(): Flow<String?> = callbackFlow {
	val watcher = doAfterTextChanged { trySend(it?.toString()).isSuccess }
	awaitClose { removeTextChangedListener(watcher) }
}