package com.android.sample.core.presentation.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

interface ClosableCoroutineScope : CoroutineScope, Closeable

class PresentationCoroutineScope(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcher
) : ClosableCoroutineScope {

    override fun close() {
        cancel()
    }
}
