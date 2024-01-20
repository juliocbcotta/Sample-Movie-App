package com.android.sample.card.router

import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableName
import com.veepee.vpcore.route.link.compose.ComposableParameter
import com.veepee.vpcore.route.link.compose.NoComposableEvent

object MovieCardScreenName : ComposableName

data class MovieCardParameter(
    val imdbId: String,
    val title: String,
    val year: String,
    val poster: String,
) : ComposableParameter

class MovieCardScreenLink(
    override val parameter: MovieCardParameter
) : ComposableLink<MovieCardScreenName, NoComposableEvent> {
    override val composableName: MovieCardScreenName = MovieCardScreenName
}
