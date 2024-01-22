package com.android.sample.core.di.presenter

import androidx.compose.runtime.compositionLocalOf
import com.android.sample.core.presentation.presenter.Presenter
import com.android.sample.core.presentation.store.Store

val LocalPresenterStore = compositionLocalOf<Store<String, Presenter>> {
    PresenterStore(100)
}

class PresenterStore(capacity: Int) : Store<String, Presenter>(
    map = createLRUMap(maxEntries = capacity,
        onRemovalOf = { entry ->
            entry.value.onClear()
        })
) {

    override fun clear() {
        map.forEach { (_, presenter) -> presenter.onClear() }
        super.clear()
    }
}

// https://stackoverflow.com/a/11469731
private fun <K, V> createLRUMap(maxEntries: Int, onRemovalOf: (Map.Entry<K, V>) -> Unit): LinkedHashMap<K, V> {
    return object : LinkedHashMap<K, V>(maxEntries * 10 / 7, 0.7f, true) {
        override fun removeEldestEntry(eldest: Map.Entry<K, V>): Boolean {
            if (size >= maxEntries) {
                onRemovalOf(eldest)
                return true
            }
            return false
        }
    }
}
