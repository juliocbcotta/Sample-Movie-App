package com.android.sample.list.presentation

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sample.core.coroutines.runSuspendCatching
import com.android.sample.core.presentation.state.factory.StateFactory
import com.android.sample.list.abstraction.Movie
import com.android.sample.list.domain.MovieRepository
import com.android.sample.list.presentation.ListState.Error
import com.android.sample.list.presentation.ListState.Loading
import com.android.sample.list.presentation.ListState.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableMovie(
    override val title: String,
    override val year: String,
    override val imdbId: String,
    override val poster: String
) : Movie, Parcelable

sealed interface ListState : Parcelable {
    @Parcelize
    data object Loading : ListState

    @Parcelize
    data class Success(val result: List<ParcelableMovie>) : ListState

    @Parcelize
    data object Error : ListState
}

@AssistedFactory
interface ListViewModelFactory {
    fun create(
        factory: StateFactory
    ): ListViewModel
}

class ListViewModel @AssistedInject constructor(
    @Assisted stateFactory: StateFactory,
    private val repository: MovieRepository
) : ViewModel() {
    private val listState = stateFactory.create<ListState>("list state", Loading)
    private val searchState = stateFactory.create("query", "")

    val state = listState.value

    init {
        // This avoids resubmission of the same query in a config change
        searchState.value.onEach { query -> search(query) }.launchIn(viewModelScope)
    }

    private fun setState(newValue: ListState) {
        listState.update(newValue)
    }

    fun submitQuery(query: String) {
        searchState.update(query)
    }

    private suspend fun search(query: String) {
        setState(Loading)
        runSuspendCatching {
            repository.search(query).search
        }.onSuccess { result ->
            setState(Success(result.asParcelables()))
        }.onFailure {
            setState(Error)
        }
    }
}

private fun List<Movie>.asParcelables(): List<ParcelableMovie> {
    return map { movie ->
        movie.toParcelable()
    }
}

private fun Movie.toParcelable(): ParcelableMovie {
    return ParcelableMovie(
        title = title,
        year = year,
        imdbId = imdbId,
        poster = poster
    )
}
