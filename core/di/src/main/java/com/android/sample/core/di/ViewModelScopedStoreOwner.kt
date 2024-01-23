package com.android.sample.core.di

import androidx.lifecycle.ViewModel
import com.android.sample.core.presentation.store.Store
import java.io.Closeable

class ViewModelScopedStoreOwner<K, V>(val store: Store<K, V>) :
    ViewModel(Closeable { store.clear() })

