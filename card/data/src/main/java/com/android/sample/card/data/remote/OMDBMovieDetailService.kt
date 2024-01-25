package com.android.sample.card.data.remote

import com.android.sample.card.data.remote.models.MovieDetailResponse
import com.android.sample.list.abstraction.data.remote.MovieDetailService
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBMovieDetailService : MovieDetailService {
    @GET("/")
    override suspend fun getMovieDetail(@Query("i") imdbId: String): MovieDetailResponse
}


