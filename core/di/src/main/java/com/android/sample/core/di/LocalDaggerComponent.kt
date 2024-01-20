package com.android.sample.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.android.sample.core.presentation.store.Store

interface DaggerComponent

val LocalDaggerComponentStore = compositionLocalOf { DaggerComponentStore() }

class DaggerComponentStore : Store<DaggerComponent>()

@Composable
fun ProvideDaggerComponentStore(key: String, content: @Composable () -> Unit) {
    val daggerStore = remember(key) { DaggerComponentStore() }
    DisposableEffect(key) {
        onDispose {
            daggerStore.clear()
        }
    }
    CompositionLocalProvider(LocalDaggerComponentStore provides daggerStore) {
        content()
    }
}
