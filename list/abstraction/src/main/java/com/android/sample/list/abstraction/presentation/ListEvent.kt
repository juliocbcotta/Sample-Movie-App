package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.MovieQuery

interface ListEvent {
    interface OnSubmitQuery {
        val query: MovieQuery
    }
}
