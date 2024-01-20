package com.android.sample.card.router

import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableName
import com.veepee.vpcore.route.link.compose.ComposableParameter
import com.veepee.vpcore.route.link.compose.NoComposableEvent

object ListScreenName : ComposableName

data class ListScreenParameter(
    val query: String,
) : ComposableParameter

class ListScreenLink(
    override val parameter: ListScreenParameter
) : ComposableLink<ListScreenName, NoComposableEvent> {
    override val composableName: ListScreenName = ListScreenName
}
