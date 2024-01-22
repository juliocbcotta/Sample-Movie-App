package com.android.sample.list.data.di

import com.android.sample.list.data.MovieRepositoryImpl
import com.android.sample.list.data.remote.OMDBMovieService
import com.android.sample.list.abstraction.domain.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class DataModule {

    @Provides
    fun provideService(retrofit: Retrofit): OMDBMovieService {
        return retrofit.create()
    }

    @Provides
    fun provideRepository(impl: MovieRepositoryImpl): MovieRepository = impl
}
