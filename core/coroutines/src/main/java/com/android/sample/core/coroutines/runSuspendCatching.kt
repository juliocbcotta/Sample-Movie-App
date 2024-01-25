package com.android.sample.core.coroutines

import kotlin.coroutines.cancellation.CancellationException
/**
 * A runCatching function that rethrows CancellationException to enable cooperative cancellation.
 * https://github.com/Kotlin/kotlinx.coroutines/issues/1814
 * */
suspend inline fun <R> runSuspendCatching(crossinline block: suspend () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
