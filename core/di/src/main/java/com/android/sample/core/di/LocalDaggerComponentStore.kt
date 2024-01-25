package com.android.sample.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.android.sample.core.di.viewmodel.composeViewModel
import com.android.sample.core.presentation.store.Store
import kotlin.reflect.KClass

interface DaggerComponent

val LocalDaggerComponentStore =
    compositionLocalOf<Store<KClass<out DaggerComponent>, DaggerComponent>> { DaggerComponentStore() }

class DaggerComponentStore : Store<KClass<out DaggerComponent>, DaggerComponent>()

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

@Composable
fun ProvideVMScopedDaggerComponentStore(
    key: String,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    content: @Composable () -> Unit
) {
    val vmStoreOwner = composeViewModel(
        key = key,
        viewModelStoreOwner = viewModelStoreOwner,
        viewModelFactory = {
            ViewModelScopedStoreOwner(store = DaggerComponentStore())
        })
    CompositionLocalProvider(LocalDaggerComponentStore provides vmStoreOwner.store) {
        content()
    }
}
