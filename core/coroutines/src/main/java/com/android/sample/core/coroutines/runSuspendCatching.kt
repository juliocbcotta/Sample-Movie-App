package com.android.sample.core.coroutines

import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <R> runSuspendCatching(crossinline block: suspend () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
