package com.android.sample.card.data.di

import com.android.sample.card.data.remote.OMDBMovieDetailService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class DataModule {

    @Provides
    fun provideService(retrofit: Retrofit): OMDBMovieDetailService {
        return retrofit.create()
    }
}
