package com.android.sample.card.data.remote

import com.android.sample.card.data.remote.models.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBMovieDetailService {
    @GET("/")
    suspend fun getMovieDetail(@Query("i") imdbId: String): MovieDetailResponse
}


