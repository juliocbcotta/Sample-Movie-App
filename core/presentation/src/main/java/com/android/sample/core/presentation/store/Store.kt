package com.android.sample.core.presentation.store

abstract class Store<T>(
    protected val map: MutableMap<String, T> = mutableMapOf()
) {
    @Suppress("UNCHECKED_CAST")
    open fun <T1 : T> getOrCreate(key: String, create: () -> T1): T1 {
        return map.getOrPut(key) { create() } as T1
    }

    open fun clear() {
        map.clear()
    }
}
