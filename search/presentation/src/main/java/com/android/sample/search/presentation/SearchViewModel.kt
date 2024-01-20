package com.android.sample.search.presentation

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.android.sample.search.presentation.SearchEvent.OnActiveChange
import com.android.sample.search.presentation.SearchEvent.OnDeleteTag
import com.android.sample.search.presentation.SearchEvent.OnQueryChange
import com.android.sample.search.presentation.SearchEvent.OnSubmitQuery
import com.android.sample.search.presentation.SearchEvent.OnToggleTag
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Immutable
data class MovieTagState(val query: String, val deletable: Boolean)

@Immutable
data class SearchState(
    val query: String,
    val active: Boolean,
    val selectedTag: MovieTagState,
    val tags: List<MovieTagState>
)

sealed interface SearchEvent {
    data class OnSubmitQuery(val query: String) : SearchEvent
    data class OnQueryChange(val query: String) : SearchEvent
    data class OnActiveChange(val active: Boolean) : SearchEvent
    data class OnDeleteTag(val tag: MovieTagState) : SearchEvent
    data class OnToggleTag(val tag: MovieTagState) : SearchEvent
}

class SearchViewModel @Inject constructor() : ViewModel() {
    private val tags = listOf("Documentary", "Interview", "Horror", "Crime", "Comedy", "Romance")
        .map { tag ->
            MovieTagState(
                tag,
                false
            )
        }
    private var searchState = SearchState(
        tags.first().query,
        false,
        tags.first(),
        tags
    )
    val state = MutableStateFlow(searchState)

    fun onEvent(event: SearchEvent) {
        when (event) {
            is OnSubmitQuery -> {
                val list = searchState.tags.toMutableList()
                if (list.none { it.query == searchState.query }) {
                    list.add(
                        0,
                        MovieTagState(
                            searchState.query,
                            deletable = true
                        )
                    )
                    searchState = searchState.copy(
                        active = false,
                        selectedTag = list.first(),
                        tags = list
                    )
                }
            }

            is OnQueryChange -> {
                searchState = searchState.copy(query = event.query)
            }

            is OnActiveChange -> {
                searchState = searchState.copy(active = event.active)
            }

            is OnDeleteTag -> {
                val index = searchState.tags.indexOf(event.tag)
                val list = searchState.tags.toMutableList()
                list.removeAt(index)
                searchState = searchState.copy(selectedTag = event.tag, tags = list)
            }

            is OnToggleTag -> {
                val list = searchState.tags.toMutableList()
                val query = if (event.tag == searchState.selectedTag) {
                    ""
                } else {
                    event.tag.query
                }
                searchState = searchState.copy(
                    query = query,
                    active = false,
                    selectedTag = event.tag,
                    tags = list
                )
            }
        }
        state.value = searchState
    }
}
