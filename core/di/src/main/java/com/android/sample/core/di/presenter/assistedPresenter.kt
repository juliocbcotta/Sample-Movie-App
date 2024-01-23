package com.android.sample.core.di.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import com.android.sample.core.presentation.presenter.Presenter

@Composable
inline fun <reified T : Presenter> assistedPresenter(
    key: String = T::class.java.simpleName,
    crossinline create: @DisallowComposableCalls () -> T
): T {
    val store = LocalPresenterStore.current
    return remember(key) {
        store.getOrCreate(key) {
            create()
        }
    }
}
