package com.android.sample.card.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.sample.card.presentation.di.DaggerMovieCardComponent
import com.android.sample.card.presentation.di.MovieCardComponent
import com.android.sample.card.presentation.state.ParcelableMovieCardEvent.RequestToReload
import com.android.sample.card.router.MovieCardParameter
import com.android.sample.core.di.LocalDaggerComponentStore
import com.android.sample.core.di.presenter.InMemoryStateFactory
import com.android.sample.core.di.presenter.assistedPresenter
import com.android.sample.core.di.viewmodel.assistedComposeViewModel
import com.android.sample.list.abstraction.MovieDetail
import com.android.sample.list.abstraction.presentation.MovieCardPresenter
import com.android.sample.list.abstraction.presentation.which

/**
 * This composable VM will stay in memory as long as the ViewModelStore exists.
 * Pros:
 *   - The state of each Card is always up to date after it is loaded for the first time.
 *   - Config changes and state restoration in process death works just fine.
 * Cons:
 *   - This might be a memory problem as we could have hundreds of cards, for each card a new VM.
 *   - It may try to save a bundle that is too big for Android in the SavedStateHandle.
 *   - Bound to Google/Android libraries.
 * */
@Composable
fun MovieCardScreen(parameter: MovieCardParameter, modifier: Modifier) {
    Box(modifier) {
        val store = LocalDaggerComponentStore.current
        MovieCardScreen(parameter.toMovieDetail(),
            assistedComposeViewModel("VM${parameter.imdbId}") { stateFactory ->
                val component = store.getOrCreate(MovieCardComponent::class) {
                    DaggerMovieCardComponent.builder().build()
                }
                component.viewModelFactory.create(
                    component.presenterFactory.create(parameter.imdbId, stateFactory)
                )
            })
    }
}

/**
 * This composable VM will stay in memory as long as the LRU cache size for presenters allows it.
 * Pros:
 *   - Limited amount of memory used. The PresenterStore controls how many can be kept alive at a time.
 *   - Unbound of Google/Android libraries. Making it  close of a multiplatform solution?
 * Cons:
 *   - This might be a memory problem if the PresenterStore cache size is too big.
 *   - This might become a UI issue if the PresenterStore cache size is too small, we may draw a card
 *   with a cleared presenter (no coroutine will run after onClear).
 *   - There is no support to restoring state in case of process death, only config changes.
 *   See : ProvidePresenterStore vs ProvideVMScopedPresenterStore
 * */
@Composable
fun MovieCardScreen2(parameter: MovieCardParameter, modifier: Modifier) {
    Box(modifier) {
        val store = LocalDaggerComponentStore.current
        MovieCardScreen(
            movieDetail = parameter.toMovieDetail(),
            presenter = assistedPresenter("Presenter${parameter.imdbId}") {
                store.getOrCreate(MovieCardComponent::class) {
                    DaggerMovieCardComponent.builder().build()
                }.presenterFactory.create(parameter.imdbId, InMemoryStateFactory)
            })
    }
}

private fun MovieCardParameter.toMovieDetail(): MovieDetail {
    val that = this
    return object : MovieDetail {
        override val title: String
            get() = that.title
        override val year: String
            get() = that.year
        override val imdbId: String
            get() = that.imdbId
        override val poster: String
            get() = that.poster
        override val plot: String
            get() = ""
    }
}

@Composable
fun MovieCardScreen(
    movieDetail: MovieDetail,
    presenter: MovieCardPresenter
) {
    val state by presenter.state.collectAsStateWithLifecycle()
    var data by remember { mutableStateOf(movieDetail) }
    MovieCardContainer(data) {
        state.which(
            onInitial = {},
            onLoading = {
                Loading()
            },
            onSuccess = { s ->
                data = s.result
            },
            onError = {
                Button(
                    onClick = {
                        presenter.onEvent(RequestToReload)
                    }) {
                    Text("Load extra info")
                }
            },
        )
    }
}

@Composable
private fun MovieCardContainer(
    movieDetail: MovieDetail,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(255.dp)
                .clipToBounds(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxHeight().weight(1.0f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieDetail.poster)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp).weight(2.0f)) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = movieDetail.title
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = movieDetail.plot,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                content()
            }
        }
    }
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
