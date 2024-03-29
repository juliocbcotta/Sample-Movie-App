package com.android.sample.card.presentation.state

import android.os.Parcelable
import com.android.sample.list.abstraction.domain.MovieDetail
import com.android.sample.list.abstraction.presentation.MovieCardEvent.RequestToReload
import com.android.sample.list.abstraction.presentation.MovieCardState.Error
import com.android.sample.list.abstraction.presentation.MovieCardState.Initial
import com.android.sample.list.abstraction.presentation.MovieCardState.Loading
import com.android.sample.list.abstraction.presentation.MovieCardState.Success
import kotlinx.parcelize.Parcelize

sealed interface ParcelableMovieCardState : Parcelable {
    @Parcelize
    data object ParcelableInitial : ParcelableMovieCardState, Initial

    @Parcelize
    data object ParcelableLoading : ParcelableMovieCardState, Loading

    @Parcelize
    data class ParcelableSuccess(
        override val result: ParcelableMovieDetail
    ) : ParcelableMovieCardState, Success

    @Parcelize
    data object ParcelableError : ParcelableMovieCardState, Error
}

@Parcelize
data class ParcelableMovieDetail(
    override val title: String,
    override val year: String,
    override val imdbId: String,
    override val poster: String,
    override val plot: String
) : MovieDetail, Parcelable

sealed interface ParcelableMovieCardEvent {
    data object ParcelableRequestToReload : ParcelableMovieCardEvent, RequestToReload
}
