package com.android.sample.core.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.android.sample.core.di.Store
import com.android.sample.core.di.ViewModelScopedStoreOwner
import com.android.sample.core.di.viewmodel.composeViewModel
import kotlin.reflect.KClass

@Composable
fun ProvideDaggerComponentStore(key: String, content: @Composable () -> Unit) {
    val daggerStore = remember(key) { DaggerComponentStore() }
    DisposableEffect(key) {
        onDispose {
            daggerStore.clear()
        }
    }
    ProvideDaggerComponentStore(daggerStore) {
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
    ProvideDaggerComponentStore(vmStoreOwner.store) {
        content()
    }
}

@Composable
fun ProvideDaggerComponentStore(
    daggerStore: Store<KClass<out DaggerComponent>, DaggerComponent>,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalDaggerComponentStore provides daggerStore) {
        content()
    }
}
