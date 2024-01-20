package com.android.sample.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.sample.search.router.SearchScreenLink
import com.android.sample.search.router.SearchScreenName
import com.veepee.vpcore.route.link.compose.ComposableEvent
import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableNameMapper

object SearchScreenNameMapper : ComposableNameMapper<SearchScreenName> {

    override val supportedNames: Array<SearchScreenName> = arrayOf(SearchScreenName)

    @Composable
    override fun Map(link: ComposableLink<SearchScreenName, ComposableEvent>, modifier: Modifier) {
        when (link) {
            is SearchScreenLink -> SearchScreenContainer(modifier)
        }
    }
}
