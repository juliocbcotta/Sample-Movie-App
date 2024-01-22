package com.android.sample.list.data

import com.android.sample.list.abstraction.domain.MovieQuery
import com.android.sample.list.abstraction.domain.MovieSearchResult
import com.android.sample.list.data.remote.OMDBMovieService
import com.android.sample.list.abstraction.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: OMDBMovieService
) : MovieRepository {
    override suspend fun search(query: MovieQuery): MovieSearchResult {
        return service.search(query.query, 1)
    }
}
