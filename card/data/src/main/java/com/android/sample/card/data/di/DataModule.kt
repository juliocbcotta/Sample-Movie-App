package com.android.sample.card.data.di

import com.android.sample.card.data.remote.OMDBMovieDetailService
import com.android.sample.list.abstraction.data.remote.MovieDetailService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class DataModule {

    @Provides
    fun provideService(retrofit: Retrofit): MovieDetailService {
        return retrofit.create<OMDBMovieDetailService>()
    }
}
