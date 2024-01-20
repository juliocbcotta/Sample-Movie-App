package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.MovieDetail
import com.android.sample.list.abstraction.presentation.MovieCardState.Error
import com.android.sample.list.abstraction.presentation.MovieCardState.Initial
import com.android.sample.list.abstraction.presentation.MovieCardState.Loading
import com.android.sample.list.abstraction.presentation.MovieCardState.Success

// This interface allows us to have different serializers for ui state,
// for instance, in Android the `MovieCardState` implementations are Parcelables
// maybe in other platform something else.
interface MovieCardStateMapper {
    val initial: Initial
    val loading: Loading
    val error: Error
    fun success(movieDetail: MovieDetail): Success
}
