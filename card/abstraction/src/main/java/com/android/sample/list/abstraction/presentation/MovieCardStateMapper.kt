package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.MovieDetail
import com.android.sample.list.abstraction.presentation.MovieCardState.Error
import com.android.sample.list.abstraction.presentation.MovieCardState.Initial
import com.android.sample.list.abstraction.presentation.MovieCardState.Loading
import com.android.sample.list.abstraction.presentation.MovieCardState.Success

interface MovieCardStateMapper {
    val initial: Initial
    val loading: Loading
    val error: Error
    fun success(movieDetail: MovieDetail): Success
}
