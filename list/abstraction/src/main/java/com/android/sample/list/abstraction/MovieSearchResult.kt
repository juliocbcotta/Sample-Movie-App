package com.android.sample.list.abstraction

interface MovieSearchResult {
    val search: List<Movie>
    val totalResults: Int
}
