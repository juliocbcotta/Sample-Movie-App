package com.android.sample.core.coroutines

import kotlinx.coroutines.CoroutineScope
import java.io.Closeable

interface ClosableCoroutineScope : CoroutineScope, Closeable
