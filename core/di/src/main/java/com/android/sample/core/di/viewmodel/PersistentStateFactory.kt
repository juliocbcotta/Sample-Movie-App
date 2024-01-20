package com.android.sample.core.di.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.android.sample.core.presentation.state.factory.State
import com.android.sample.core.presentation.state.factory.StateFactory

fun SavedStateHandle.asStateFactory(): StateFactory {
    return PersistentStateFactory(this)
}

class PersistentStateFactory(private val savedStateHandle: SavedStateHandle) : StateFactory {
    override fun <T : Any> create(key: String, initialValue: T): State<T> {
        return State(
            value = savedStateHandle.getStateFlow(key = key, initialValue = initialValue),
            update = { newValue -> savedStateHandle[key] = newValue })
    }
}
