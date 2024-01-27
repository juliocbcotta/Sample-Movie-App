package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.MovieQuery

sealed interface ListEvent {
    interface OnSubmitQuery : ListEvent {
        val query: MovieQuery
    }
}
