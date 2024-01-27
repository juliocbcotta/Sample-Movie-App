package com.android.sample.list.ui

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
import com.android.sample.core.di.component.ProvideDaggerComponentStore
import com.android.sample.core.di.component.rememberDaggerComponent
import com.android.sample.core.di.presenter.InMemoryStateFactory
import com.android.sample.core.di.presenter.ProvideVMScopedPresenterStore
import com.android.sample.core.di.presenter.rememberPresenter
import com.android.sample.core.di.viewmodel.assistedComposeViewModel
import com.android.sample.list.abstraction.domain.Movie
import com.android.sample.list.abstraction.presentation.ListPresenter
import com.android.sample.list.abstraction.presentation.ListState
import com.android.sample.list.presentation.di.DaggerListComponent
import com.android.sample.list.presentation.state.ParcelableListEvent.ParcelableOnSubmitEvent
import com.android.sample.list.presentation.state.ParcelableMovieQuery
import com.veepee.vpcore.route.link.compose.ComposableFor

@Immutable
data class MovieTag(val query: String)

@Composable
fun ListScreenContainer(modifier: Modifier, parameter: ListScreenParameter) {
    Box(modifier) {
        val component = rememberDaggerComponent { DaggerListComponent.builder().build() }
        ListScreen(parameter.toMovieTag(),
            assistedComposeViewModel("ListVM") { stateFactory ->
                component.viewModelFactory.create(
                    component.presenterFactory.create(stateFactory)
                )
            })
    }
}

@Composable
fun ListScreenContainer2(modifier: Modifier, parameter: ListScreenParameter) {
    Box(modifier) {
        ListScreen(parameter.toMovieTag(),
            rememberPresenter {
                DaggerListComponent.create()
                    .presenterFactory.create(InMemoryStateFactory)
            })
    }
}

private fun ListScreenParameter.toMovieTag(): MovieTag {
    return MovieTag(query)
}

@Composable
fun ListScreen(
    selectedTag: MovieTag,
    viewModel: ListPresenter
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(selectedTag) {
        viewModel.onEvent(ParcelableOnSubmitEvent(selectedTag.toParcelableQuery()))
    }
    when (val s = state) {
        is ListState.Error -> Text("Fuuuuuuuu, some network error or some shit")
        is ListState.Loading -> Loading()
        is ListState.Success -> {
            if (s.result.isEmpty()) {
                Text("No movies found for your query")
            } else {
                /**
                 * ProvideDaggerComponentStore vs ProvideVMScopedDaggerComponentStore
                 *
                 * ProvideDaggerComponentStore will cache your DaggerComponents
                 * and will live as long as this current composition
                 *
                 * ProvideVMScopedDaggerComponentStore in memory while the ViewModel hosting the store is around.
                 *
                 * I am placing this here because each Card will reuse the dagger component generated at the first card.
                 *
                 * */
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
                     * Just to be clear: This should be used for very long lists,
                     * if you have 20 items in a list, it is worth to keep 20 presenters in memory,
                     * if you have 100 items in the list, it is a good compromise to keep the last 40 presenters
                     * in memory while the list is being displayed.
                     *
                     * */
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

private fun MovieTag.toParcelableQuery(): ParcelableMovieQuery {
    return ParcelableMovieQuery(query)
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
