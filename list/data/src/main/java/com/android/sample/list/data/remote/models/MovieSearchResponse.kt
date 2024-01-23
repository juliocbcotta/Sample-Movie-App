package com.android.sample.list.data.remote.models

import com.android.sample.list.abstraction.domain.Movie
import com.android.sample.list.abstraction.domain.MovieSearchResult
import com.google.gson.annotations.SerializedName

// NOTE: The API returns a 200, but changes the response model to an error when it can't find a movie
// So I made search optional and fixed the interface implementation with a default value.
data class MovieSearchResponse(
    @SerializedName("Search")
    val nullableSearch: List<MovieResponse>?,
    override val totalResults: Int
) : MovieSearchResult {
    override val search: List<Movie>
        get() = nullableSearch ?: emptyList()
}
