package com.android.sample.list.presentation.state

import android.os.Parcelable
import com.android.sample.list.abstraction.domain.Movie
import com.android.sample.list.abstraction.presentation.ListState.Error
import com.android.sample.list.abstraction.presentation.ListState.Loading
import com.android.sample.list.abstraction.presentation.ListState.Success
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableMovie(
    override val title: String,
    override val year: String,
    override val imdbId: String,
    override val poster: String
) : Movie, Parcelable

sealed interface ParcelableListState : Parcelable {
    @Parcelize
    data object ParcelableLoading : Loading, ParcelableListState

    @Parcelize
    data class ParcelableSuccess(override val result: List<ParcelableMovie>) : Success, ParcelableListState

    @Parcelize
    data object ParcelableError : Error, ParcelableListState
}

