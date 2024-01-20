package com.android.sample.core.presentation.state.factory

import kotlinx.coroutines.flow.StateFlow

data class State<T>(val value: StateFlow<T>, val update: (T) -> Unit)

interface StateFactory {
    fun <T : Any> create(key: String, initialValue: T): State<T>
}
