package com.android.sample.list.data

import com.android.sample.list.abstraction.MovieSearchResult
import com.android.sample.list.data.remote.MovieService
import com.android.sample.list.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {
    override suspend fun search(query: String): MovieSearchResult {
        return service.search(query, 1)
    }
}
