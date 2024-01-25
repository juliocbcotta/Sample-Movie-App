package com.android.sample.core.di.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.sample.core.presentation.state.factory.StateFactory

@Composable
@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> composeViewModel(
    key: String?,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    crossinline viewModelFactory: @DisallowComposableCalls () -> T
): T = viewModel(
    modelClass = T::class.java,
    viewModelStoreOwner = viewModelStoreOwner,
    key = key,
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelFactory() as T
        }
    }
)

@Composable
inline fun <reified T : ViewModel> assistedComposeViewModel(
    key: String,
    crossinline factory: @DisallowComposableCalls (StateFactory) -> T
): T = viewModel(key = key) {
    val savedStateHandle = createSavedStateHandle()
    factory(savedStateHandle.asStateFactory())
}
