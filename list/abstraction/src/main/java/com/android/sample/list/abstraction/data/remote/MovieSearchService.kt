package com.android.sample.list.abstraction.data.remote

import com.android.sample.list.abstraction.domain.MovieSearchResult

interface MovieSearchService {
    suspend fun search(query: String, page: Int): MovieSearchResult
}
