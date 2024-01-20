package com.android.sample.core.networking.di

import com.android.sample.core.networking.ApiKeyInterceptor
import com.android.sample.core.networking.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.omdbapi.com/")
            .build()
    }

    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Named("apiKeyInterceptor")
    fun providesApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    fun providesHttpLoggingInterceptor(logLevel: Level): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = logLevel
        return interceptor
    }

    @Provides
    fun provideLogLevel(): Level {
        return if (BuildConfig.DEBUG) {
            Level.BODY
        } else {
            Level.NONE
        }
    }
}
