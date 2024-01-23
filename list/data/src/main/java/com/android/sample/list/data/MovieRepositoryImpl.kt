package com.android.sample.list.data

import com.android.sample.list.abstraction.data.remote.MovieSearchService
import com.android.sample.list.abstraction.domain.MovieQuery
import com.android.sample.list.abstraction.domain.MovieRepository
import com.android.sample.list.abstraction.domain.MovieSearchResult
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieSearchService
) : MovieRepository {
    override suspend fun search(query: MovieQuery): MovieSearchResult {
        return service.search(query.query, 1)
    }
}
