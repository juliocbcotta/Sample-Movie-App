package com.android.sample.card.presentation

import androidx.lifecycle.ViewModel
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.presentation.MovieCardPresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface MovieCardViewModelFactory {
    fun create(
        presenter: MovieCardPresenter
    ): MovieCardViewModel
}

class MovieCardViewModel @AssistedInject constructor(
    @Assisted presenter: MovieCardPresenter
) : ViewModel(),
    MovieCardPresenter by presenter {

    override fun onCleared() {
        super.onCleared()
        onClear()
    }
}
