package com.android.sample.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.sample.card.router.ListScreenLink
import com.android.sample.card.router.ListScreenParameter
import com.android.sample.core.di.component.rememberDaggerComponent
import com.android.sample.core.di.viewmodel.composeViewModel
import com.android.sample.search.presentation.SearchEvent.OnActiveChange
import com.android.sample.search.presentation.SearchEvent.OnDeleteTag
import com.android.sample.search.presentation.SearchEvent.OnQueryChange
import com.android.sample.search.presentation.SearchEvent.OnSubmitQuery
import com.android.sample.search.presentation.SearchEvent.OnToggleTag
import com.android.sample.search.presentation.di.DaggerSearchComponent
import com.veepee.vpcore.route.link.compose.ComposableFor

@Composable
fun SearchScreenContainer(modifier: Modifier) {
    Box(modifier) {
        val component = rememberDaggerComponent { DaggerSearchComponent.builder().build() }
        val viewModel: SearchViewModel = composeViewModel(
            key = "Search VM key",
            viewModelFactory = {
                component.viewModel
            }
        )
        val searchState by viewModel.state.collectAsStateWithLifecycle()

        SearchScreen(searchState) { event ->
            viewModel.onEvent(event)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen(searchState: SearchState, event: (SearchEvent) -> Unit) {
    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = searchState.query,
            active = searchState.active,
            onQueryChange = { query ->
                event(OnQueryChange(query))
            },
            onSearch = { query ->
                event(OnSubmitQuery(query))
            },
            onActiveChange = { active ->
                event(OnActiveChange(active))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (searchState.active) {
                    CloseIcon(event)
                }
            },
            content = {
                MovieTags(searchState.selectedTag,
                    searchState.tags,
                    onChange = { tag ->
                        event(OnToggleTag(tag))
                    },
                    onDelete = { tag ->
                        event(OnDeleteTag(tag))
                    })
            }
        )
    }) { padding ->
        Box(Modifier.padding(padding)) {
            ComposableFor(ListScreenLink(searchState.toLinkScreenParameter()))
        }
    }
}

private fun SearchState.toLinkScreenParameter(): ListScreenParameter {
    return ListScreenParameter(query)
}

@Composable
private fun CloseIcon(event: (OnActiveChange) -> Unit) {
    Icon(
        modifier = Modifier.clickable {
            event(OnActiveChange(false))
        },
        imageVector = Icons.Default.Close,
        contentDescription = null
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieTags(
    selectedState: MovieTagState,
    tags: List<MovieTagState>,
    onChange: (tagsState: MovieTagState) -> Unit,
    onDelete: (tagsState: MovieTagState) -> Unit
) {
    FlowRow(Modifier.padding(horizontal = 16.dp)) {
        tags.forEach { tag ->
            MovieTag(movieTag = tag,
                selected = selectedState == tag,
                onClick = {
                    onChange(tag)
                },
                onDelete = {
                    onDelete(tag)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieTag(
    movieTag: MovieTagState,
    selected: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    FilterChip(
        modifier = Modifier.padding(4.dp),
        onClick = { onClick() },
        label = {
            Text(movieTag.query)
        },
        selected = selected,
        leadingIcon = {
            if (selected) {
                CheckIcon()
            }
        },
        trailingIcon = {
            if (movieTag.deletable) {
                DeleteIcon(onDelete)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckIcon() {
    Icon(
        imageVector = Icons.Default.Check,
        contentDescription = null,
        modifier = Modifier.size(FilterChipDefaults.IconSize)
    )
}

@Composable
private fun DeleteIcon(onDelete: () -> Unit) {
    Icon(
        modifier = Modifier.clickable {
            onDelete()
        },
        imageVector = Icons.Default.Delete, contentDescription = null
    )
}
