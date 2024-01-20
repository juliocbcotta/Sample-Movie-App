package com.android.sample.card.data.remote.models

import com.android.sample.list.abstraction.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("Title")
    override val title: String,
    @SerializedName("Year")
    override val year: String,
    @SerializedName("imdbID")
    override val imdbId: String,
    @SerializedName("Poster")
    override val poster: String,
    @SerializedName("Plot")
    override val plot: String
) : MovieDetail


