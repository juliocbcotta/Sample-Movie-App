package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.MovieDetail

sealed interface MovieCardState {

    interface Initial : MovieCardState

    interface Loading : MovieCardState

    interface Success : MovieCardState {
        val result: MovieDetail
    }

    interface Error : MovieCardState
}

sealed interface MovieCardEvent {
    interface RequestToReload : MovieCardEvent
}
