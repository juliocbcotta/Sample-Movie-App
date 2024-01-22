package com.android.sample.list.presentation

import com.android.sample.core.coroutines.runSuspendCatching
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.domain.MovieQuery
import com.android.sample.list.abstraction.presentation.ListEvent
import com.android.sample.list.abstraction.presentation.ListEvent.OnSubmitQuery
import com.android.sample.list.abstraction.presentation.ListPresenter
import com.android.sample.list.abstraction.presentation.ListState
import com.android.sample.list.abstraction.presentation.ListStateMapper
import com.android.sample.list.abstraction.presentation.MovieQueryStateMapper
import com.android.sample.list.abstraction.domain.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AssistedFactory
interface ListPresenterFactory {
    fun create(
        factory: StateFactory
    ): ListPresenterImpl
}

class ListPresenterImpl @AssistedInject constructor(
    @Assisted stateFactory: StateFactory,
    private val stateMapper: ListStateMapper,
    private val repository: MovieRepository,
    private val scope: CoroutineScope,
    queryMapper: MovieQueryStateMapper,
) : ListPresenter {

    // We save the current loaded content in the state factory.
    private val listState = stateFactory.create<ListState>("list state", stateMapper.loading)

    // and also the search state, this will avoid repeated queries and resubmission
    // when coming back from a soft kill.
    private val searchState = stateFactory.create("query", queryMapper.query(""))

    override fun onEvent(event: ListEvent) {
        when (event) {
            is OnSubmitQuery -> {
                submitQuery(event.query)
            }
        }
    }

    override val state = listState.value

    init {
        searchState.value.onEach { query -> search(query) }.launchIn(scope)
    }

    private fun setState(newValue: ListState) {
        listState.update(newValue)
    }

    private fun submitQuery(query: MovieQuery) {
        searchState.update(query)
    }

    private suspend fun search(query: MovieQuery) {
        setState(stateMapper.loading)
        runSuspendCatching {
            repository.search(query).search
        }.onSuccess { result ->
            setState(stateMapper.success(result))
        }.onFailure {
            setState(stateMapper.error)
        }
    }

    override fun onClear() {
        scope.cancel()
    }
}

