package com.android.sample.card.presentation

import androidx.lifecycle.ViewModel
import com.android.sample.card.data.remote.MovieDetailService
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.presentation.MovieCardPresenter
import com.android.sample.list.abstraction.presentation.MovieCardStateMapper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope

@AssistedFactory
interface MovieCardViewModelFactory {
    fun create(
        imdbId: String,
        factory: StateFactory
    ): MovieCardViewModel
}

class MovieCardViewModel @AssistedInject constructor(
    @Assisted imdbId: String,
    @Assisted stateFactory: StateFactory,
    stateMapper: MovieCardStateMapper,
    scope: CoroutineScope,
    service: MovieDetailService,
) : ViewModel(),
    MovieCardPresenter by MovieCardPresenterImpl(
        imdbId,
        stateFactory,
        stateMapper,
        scope,
        service
    ) {

    override fun onCleared() {
        super.onCleared()
        onClear()
    }
}
