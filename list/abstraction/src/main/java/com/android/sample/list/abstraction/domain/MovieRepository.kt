package com.android.sample.list.abstraction.domain

interface MovieRepository {
    suspend fun search(query: MovieQuery): MovieSearchResult
}
