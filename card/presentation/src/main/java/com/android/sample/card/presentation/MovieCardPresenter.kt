package com.android.sample.card.presentation

import com.android.sample.card.data.remote.OMDBMovieDetailService
import com.android.sample.core.coroutines.runSuspendCatching
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.presentation.MovieCardEvent
import com.android.sample.list.abstraction.presentation.MovieCardEvent.RequestToReload
import com.android.sample.list.abstraction.presentation.MovieCardPresenter
import com.android.sample.list.abstraction.presentation.MovieCardState
import com.android.sample.list.abstraction.presentation.MovieCardStateMapper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AssistedFactory
interface MovieCardPresenterFactory {
    fun create(
        imdbId: String,
        stateFactory: StateFactory,
    ): MovieCardPresenterImpl
}


class MovieCardPresenterImpl @AssistedInject constructor(
    @Assisted private val imdbId: String,
    @Assisted private val stateFactory: StateFactory,
    private val stateMapper: MovieCardStateMapper,
    private val scope: CoroutineScope,
    private val service: OMDBMovieDetailService,
) : MovieCardPresenter {

    private val _state = stateFactory.create<MovieCardState>(imdbId, stateMapper.initial)

    override val state = _state.value

    private fun setState(newState: MovieCardState) {
        _state.update(newState)
    }

    init {
        if (state.value == stateMapper.initial) {
            load()
        }
    }

    private fun load() {
        setState(stateMapper.loading)
        scope.launch {
            runSuspendCatching {
                service.getMovieDetail(imdbId)
            }.onSuccess { result ->
                setState(stateMapper.success(result))
            }.onFailure {
                setState(stateMapper.error)
            }
        }
    }

    override fun onEvent(event: MovieCardEvent) {
        when (event) {
            is RequestToReload -> load()
        }
    }

    override fun onClear() {
        scope.cancel()
    }
}
