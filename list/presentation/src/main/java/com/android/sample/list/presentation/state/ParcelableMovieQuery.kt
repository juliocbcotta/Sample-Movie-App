package com.android.sample.list.presentation.state

import android.os.Parcelable
import com.android.sample.list.abstraction.domain.MovieQuery
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableMovieQuery(override val query: String) : MovieQuery, Parcelable
