package com.android.sample.core.di.component

import androidx.compose.runtime.compositionLocalOf
import com.android.sample.core.di.Store
import kotlin.reflect.KClass

interface DaggerComponent

val LocalDaggerComponentStore =
    compositionLocalOf<Store<KClass<out DaggerComponent>, DaggerComponent>> { DaggerComponentStore() }

class DaggerComponentStore : Store<KClass<out DaggerComponent>, DaggerComponent>()
