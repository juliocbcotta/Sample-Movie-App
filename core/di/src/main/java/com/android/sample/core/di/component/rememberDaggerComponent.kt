package com.android.sample.core.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember

@Composable
inline fun <reified T : DaggerComponent> rememberDaggerComponent(
    crossinline create: @DisallowComposableCalls () -> T
): T {
    val key = T::class
    val store = LocalDaggerComponentStore.current
    return remember(key) {
        store.getOrCreate(key) {
            create()
        }
    }
}
