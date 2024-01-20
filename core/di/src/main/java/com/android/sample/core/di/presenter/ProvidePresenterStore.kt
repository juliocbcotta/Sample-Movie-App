package com.android.sample.core.di.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.android.sample.core.di.ViewModelScopedStoreOwner
import com.android.sample.core.di.viewmodel.composeViewModel
import com.android.sample.core.presentation.presenter.Presenter
import com.android.sample.core.presentation.store.Store

@Composable
fun ProvidePresenterStore(capacity: Int, key: String, content: @Composable () -> Unit) {
    val presenterStore = remember(capacity, key) {
        PresenterStore(capacity)
    }
    DisposableEffect(key) {
        onDispose {
            presenterStore.clear()
        }
    }
    ProvidePresenterStore(presenterStore) {
        content()
    }
}

@Composable
fun ProvideVMScopedPresenterStore(
    capacity: Int,
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
            val store = PresenterStore(capacity)
            ViewModelScopedStoreOwner(store = store)
        })
    ProvidePresenterStore(vmStoreOwner.store) {
        content()
    }
}

@Composable
fun ProvidePresenterStore(presenterStore: Store<Presenter>, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalPresenterStore provides presenterStore) {
        content()
    }
}
