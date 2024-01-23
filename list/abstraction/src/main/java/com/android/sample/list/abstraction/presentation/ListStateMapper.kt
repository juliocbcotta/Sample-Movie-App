package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.Movie

interface ListStateMapper {
    val loading: ListState.Loading
    val error: ListState.Error
    fun success(result: List<Movie>): ListState.Success
}
