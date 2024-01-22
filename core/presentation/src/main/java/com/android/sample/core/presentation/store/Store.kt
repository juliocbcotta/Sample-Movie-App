package com.android.sample.core.presentation.store

abstract class Store<K, V>(
    protected val map: MutableMap<K, V> = mutableMapOf()
) {
    @Suppress("UNCHECKED_CAST")
    open fun <T1 : V> getOrCreate(key: K, create: () -> T1): T1 {
        return map.getOrPut(key) { create() } as T1
    }

    open fun clear() {
        map.clear()
    }
}
