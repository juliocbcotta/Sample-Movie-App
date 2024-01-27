package com.android.sample.card.presentation.event

import com.android.sample.card.presentation.state.ParcelableMovieCardEvent.ParcelableRequestToReload
import com.android.sample.list.abstraction.presentation.MovieCardEvent.RequestToReload
import com.android.sample.list.abstraction.presentation.MovieCardEventMapper
import javax.inject.Inject

class ParcelableMovieCardEventMapper @Inject constructor() : MovieCardEventMapper {
    override val requestToReload: RequestToReload = ParcelableRequestToReload
}
