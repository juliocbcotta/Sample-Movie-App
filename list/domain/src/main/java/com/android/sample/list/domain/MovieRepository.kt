package com.android.sample.list.domain

import com.android.sample.list.abstraction.MovieSearchResult

interface MovieRepository {
    suspend fun search(query: String): MovieSearchResult
}
