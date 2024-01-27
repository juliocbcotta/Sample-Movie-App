package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.presentation.MovieCardEvent.RequestToReload

interface MovieCardEventMapper {
    val requestToReload: RequestToReload
}
