package com.android.sample.list.abstraction.presentation

import com.android.sample.list.abstraction.domain.MovieQuery

interface MovieQueryStateMapper {
    fun query(query: MovieQuery): MovieQuery
    fun query(query: String): MovieQuery
}
