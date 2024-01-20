package com.android.sample.core.di.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.android.sample.core.presentation.presenter.Presenter

@Composable
fun <T : Presenter> assistedPresenter(
    key: String,
    create: () -> T
): T {
    val store = LocalPresenterStore.current
    return remember(key) {
        store.getOrCreate(key) {
            create()
        }
    }
}
