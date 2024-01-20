package com.android.sample.list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.sample.card.router.ListScreenParameter
import com.android.sample.card.router.MovieCardParameter
import com.android.sample.card.router.MovieCardScreenLink
import com.android.sample.core.di.ProvideDaggerComponentStore
import com.android.sample.core.di.presenter.ProvidePresenterStore
import com.android.sample.core.di.presenter.ProvideVMScopedPresenterStore
import com.android.sample.core.di.viewmodel.assistedComposeViewModel
import com.android.sample.list.abstraction.Movie
import com.android.sample.list.presentation.di.DaggerListComponent
import com.veepee.vpcore.route.link.compose.ComposableFor

@Immutable
data class MovieTag(val query: String)

@Composable
fun ListScreenContainer(modifier: Modifier, parameter: ListScreenParameter) {
    Box(modifier) {
        ListScreen(parameter.toMovieTag())
    }
}

private fun ListScreenParameter.toMovieTag(): MovieTag {
    return MovieTag(query)
}

@Composable
fun ListScreen(
    selectedTag: MovieTag,
    viewModel: ListViewModel = assistedComposeViewModel("ListVM") { stateFactory ->
        DaggerListComponent.create().viewModelFactory.create(stateFactory)
    }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(selectedTag) {
        viewModel.submitQuery(selectedTag.query)
    }
    when (val s = state) {
        ListState.Error -> Text("Fuuuuuuuu, some network error or some shit")
        ListState.Loading -> Loading()
        is ListState.Success -> {
            if (s.result.isEmpty()) {
                Text("No movies found for your query")
            } else {
                ProvideDaggerComponentStore("list") {
                    /**
                     * ProvidePresenterStore vs ProvideVMScopedPresenterStore
                     *
                     * ProvidePresenterStore will keep it's presenters in memory
                     * while the current composable is not disposed and there is capacity.
                     *
                     * ProvideVMScopedPresenterStore will keep it's presenters in memory while
                     * the ViewModel hosting the store is around and there is capacity.
                     *
                     * Both Stores use a capacity to remove the eldest Presenter in a LRU fashion.
                     *
                     * */
                    ProvidePresenterStore(9, "list") {}
                    ProvideVMScopedPresenterStore(9, "list") {
                        LazyColumn {
                            items(s.result) { movie ->
                                ComposableFor(
                                    MovieCardScreenLink(
                                        movie.toMovieCardParameter()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Movie.toMovieCardParameter(): MovieCardParameter {
    return MovieCardParameter(
        imdbId = imdbId,
        title = title,
        year = year,
        poster = poster
    )
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
