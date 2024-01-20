package com.android.sample.core.di.presenter

import com.android.sample.core.presentation.state.factory.State
import com.android.sample.core.presentation.state.factory.StateFactory
import kotlinx.coroutines.flow.MutableStateFlow

object InMemoryStateFactory : StateFactory {
    override fun <T : Any> create(key: String, initialValue: T): State<T> {
        val flow = MutableStateFlow(initialValue)
        return State(
            value = flow,
            update = { newValue ->
                flow.value = newValue
            })
    }
}
