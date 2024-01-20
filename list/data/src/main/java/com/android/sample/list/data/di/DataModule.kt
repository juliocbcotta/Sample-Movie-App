package com.android.sample.list.data.di

import com.android.sample.list.data.MovieRepositoryImpl
import com.android.sample.list.data.remote.MovieService
import com.android.sample.list.domain.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class DataModule {

    @Provides
    fun provideService(retrofit: Retrofit): MovieService {
        return retrofit.create()
    }

    @Provides
    fun provideRepository(impl: MovieRepositoryImpl): MovieRepository = impl
}
