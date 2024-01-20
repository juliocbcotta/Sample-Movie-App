package com.android.sample.core.presentation.coroutines

import com.android.sample.core.coroutines.ClosableCoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class PresentationCoroutineScope(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcher
) : ClosableCoroutineScope {

    override fun close() {
        coroutineContext.cancel()
    }
}
