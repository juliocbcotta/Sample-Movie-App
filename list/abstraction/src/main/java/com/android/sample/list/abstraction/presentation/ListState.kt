package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.Movie

sealed interface ListState {

    interface Loading : ListState

    interface Success : ListState {
        val result: List<Movie>
    }

    interface Error : ListState
}
