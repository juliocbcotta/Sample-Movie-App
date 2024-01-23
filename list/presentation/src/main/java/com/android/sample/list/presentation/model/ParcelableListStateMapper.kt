package com.android.sample.list.presentation.model

import com.android.sample.list.abstraction.domain.Movie
import com.android.sample.list.abstraction.presentation.ListState.Error
import com.android.sample.list.abstraction.presentation.ListState.Loading
import com.android.sample.list.abstraction.presentation.ListState.Success
import com.android.sample.list.abstraction.presentation.ListStateMapper
import com.android.sample.list.presentation.model.ParcelableListState.ParcelableError
import com.android.sample.list.presentation.model.ParcelableListState.ParcelableLoading
import com.android.sample.list.presentation.model.ParcelableListState.ParcelableSuccess
import javax.inject.Inject

class ParcelableListStateMapper @Inject constructor() : ListStateMapper {
    override val loading: Loading = ParcelableLoading
    override val error: Error = ParcelableError
    override fun success(result: List<Movie>): Success {
        return ParcelableSuccess(result.asParcelables())
    }
}

private fun List<Movie>.asParcelables(): List<ParcelableMovie> {
    return map { movie ->
        movie.toParcelable()
    }
}

private fun Movie.toParcelable(): ParcelableMovie {
    return ParcelableMovie(
        title = title,
        year = year,
        imdbId = imdbId,
        poster = poster
    )
}
