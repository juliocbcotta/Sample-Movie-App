package com.android.sample.list.data.remote

import com.android.sample.list.data.remote.models.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun search(@Query("s") query: String, @Query("page") page: Int): MovieSearchResponse
}


