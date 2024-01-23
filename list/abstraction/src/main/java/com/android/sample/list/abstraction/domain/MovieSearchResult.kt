package com.android.sample.list.abstraction.domain

interface MovieSearchResult {
    val search: List<Movie>
    val totalResults: Int
}
