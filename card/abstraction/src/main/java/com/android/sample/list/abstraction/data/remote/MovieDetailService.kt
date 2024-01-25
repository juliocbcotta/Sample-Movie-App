package com.android.sample.list.abstraction.data.remote

import com.android.sample.list.abstraction.domain.MovieDetail

interface MovieDetailService {

    suspend fun getMovieDetail(imdbId: String): MovieDetail
}
