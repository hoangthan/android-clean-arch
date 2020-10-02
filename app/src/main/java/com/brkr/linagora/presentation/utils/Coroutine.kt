package com.brkr.linagora.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/**
 * Create an coroutine scope and run the function was passed in on Main Dispatcher.
 */
inline fun ui(crossinline action: suspend () -> Unit) = CoroutineScope(Main).launch {
    action()
}

/**
 * Create an coroutine scope and run the function was passed in on IO Dispatcher.
 */
inline fun io(crossinline action: suspend () -> Unit) = CoroutineScope(IO).launch {
    action()
}
