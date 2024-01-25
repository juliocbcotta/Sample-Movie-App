package com.android.sample.card.presentation.state

import com.android.sample.card.presentation.state.ParcelableMovieCardState.ParcelableError
import com.android.sample.card.presentation.state.ParcelableMovieCardState.ParcelableInitial
import com.android.sample.card.presentation.state.ParcelableMovieCardState.ParcelableLoading
import com.android.sample.card.presentation.state.ParcelableMovieCardState.ParcelableSuccess
import com.android.sample.list.abstraction.domain.MovieDetail
import com.android.sample.list.abstraction.presentation.MovieCardState
import com.android.sample.list.abstraction.presentation.MovieCardStateMapper
import javax.inject.Inject

class ParcelableMovieCardStateMapper @Inject constructor() : MovieCardStateMapper {

    override val initial: MovieCardState.Initial = ParcelableInitial

    override val loading: MovieCardState.Loading = ParcelableLoading

    override val error: MovieCardState.Error = ParcelableError

    override fun success(movieDetail: MovieDetail): MovieCardState.Success {
        return ParcelableSuccess(movieDetail.asParcelable())
    }
}

fun MovieDetail.asParcelable(): ParcelableMovieDetail {
    return ParcelableMovieDetail(
        title = title,
        year = year,
        imdbId = imdbId,
        poster = poster,
        plot = plot
    )
}
