package com.android.sample.list.data.di

import com.android.sample.list.abstraction.data.remote.MovieSearchService
import com.android.sample.list.abstraction.domain.MovieRepository
import com.android.sample.list.data.MovieRepositoryImpl
import com.android.sample.list.data.remote.OMDBMovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class DataModule {

    @Provides
    fun provideOMDBService(retrofit: Retrofit): OMDBMovieService {
        return retrofit.create()
    }

    @Provides
    fun provideService(impl: OMDBMovieService): MovieSearchService = impl

    @Provides
    fun provideRepository(impl: MovieRepositoryImpl): MovieRepository = impl
}
