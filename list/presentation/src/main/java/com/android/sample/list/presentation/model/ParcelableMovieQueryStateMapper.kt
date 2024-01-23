package com.android.sample.list.presentation.model

import com.android.sample.list.abstraction.domain.MovieQuery
import com.android.sample.list.abstraction.presentation.MovieQueryStateMapper
import javax.inject.Inject

class ParcelableMovieQueryStateMapper @Inject constructor() : MovieQueryStateMapper {
    override fun query(query: MovieQuery): MovieQuery {
        return ParcelableMovieQuery(query.query)
    }

    override fun query(query: String): MovieQuery {
        return ParcelableMovieQuery(query)
    }
}
