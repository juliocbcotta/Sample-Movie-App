package com.android.sample.list.abstraction.presentation

import com.android.sample.core.presentation.presenter.Presenter
import kotlinx.coroutines.flow.StateFlow

interface MovieCardPresenter : Presenter {
    val state: StateFlow<MovieCardState>
    fun onEvent(event: MovieCardEvent)
}
