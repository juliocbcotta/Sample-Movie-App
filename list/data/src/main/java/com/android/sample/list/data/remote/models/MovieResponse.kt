package com.android.sample.list.data.remote.models

import com.android.sample.list.abstraction.domain.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Title")
    override val title: String,
    @SerializedName("Year")
    override val year: String,
    @SerializedName("imdbID")
    override val imdbId: String,
    @SerializedName("Poster")
    override val poster: String
) : Movie


