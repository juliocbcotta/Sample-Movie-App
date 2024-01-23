package com.android.sample.list.data.remote

import com.android.sample.list.abstraction.data.remote.MovieSearchService
import com.android.sample.list.data.remote.models.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBMovieService : MovieSearchService {

    @GET("/")
    override suspend fun search(@Query("s") query: String, @Query("page") page: Int): MovieSearchResponse
}


