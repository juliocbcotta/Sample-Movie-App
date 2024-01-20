package com.android.sample.card.presentation.di

import com.android.sample.card.presentation.state.ParcelableMovieCardStateMapper
import com.android.sample.core.di.presenter.InMemoryStateFactory
import com.android.sample.core.presentation.coroutines.PresentationCoroutineScope
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.presentation.MovieCardStateMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class MovieCardModule {

    @Provides
    fun provideCoroutineScope(): CoroutineScope = PresentationCoroutineScope()

    @Provides
    fun provideStateFactory(): StateFactory = InMemoryStateFactory

    @Provides
    fun provideMovieCardStateMapper(): MovieCardStateMapper = ParcelableMovieCardStateMapper()
}
