package com.android.sample.core.di

import androidx.lifecycle.ViewModel
import com.android.sample.core.presentation.store.Store
import java.io.Closeable

class ViewModelScopedStoreOwner<T>(val store: Store<T>) :
    ViewModel(Closeable { store.clear() })

