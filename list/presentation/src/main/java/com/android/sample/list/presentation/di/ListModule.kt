package com.android.sample.list.presentation.di

import com.android.sample.core.presentation.coroutines.PresentationCoroutineScope
import com.android.sample.list.abstraction.presentation.ListStateMapper
import com.android.sample.list.abstraction.presentation.MovieQueryStateMapper
import com.android.sample.list.presentation.state.ParcelableListStateMapper
import com.android.sample.list.presentation.state.ParcelableMovieQueryStateMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class ListModule {

    @Provides
    fun provideCoroutineScope(): CoroutineScope = PresentationCoroutineScope()

    @Provides
    fun provideListMapper(impl: ParcelableListStateMapper): ListStateMapper = impl

    @Provides
    fun provideQueryMapper(impl: ParcelableMovieQueryStateMapper): MovieQueryStateMapper = impl
}
