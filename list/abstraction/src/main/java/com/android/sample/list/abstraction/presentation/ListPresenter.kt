package com.android.sample.list.abstraction.presentation

import com.android.sample.core.presentation.presenter.Presenter
import kotlinx.coroutines.flow.StateFlow

interface ListPresenter : Presenter {
    val state: StateFlow<ListState>
    fun onEvent(event: ListEvent)
}
