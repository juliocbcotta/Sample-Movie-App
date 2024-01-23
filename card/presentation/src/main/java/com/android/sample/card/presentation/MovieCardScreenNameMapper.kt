package com.android.sample.card.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.sample.card.router.MovieCardScreenLink
import com.android.sample.card.router.MovieCardScreenName
import com.veepee.vpcore.route.link.compose.ComposableEvent
import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableNameMapper

object MovieCardScreenNameMapper : ComposableNameMapper<MovieCardScreenName> {

    override val supportedNames: Array<MovieCardScreenName> = arrayOf(MovieCardScreenName)

    @Composable
    override fun Map(link: ComposableLink<MovieCardScreenName, ComposableEvent>, modifier: Modifier) {
        when (link) {
            is MovieCardScreenLink -> MovieCardScreen2(link.parameter, modifier)
        }
    }
}
