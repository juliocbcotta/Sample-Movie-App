package com.android.sample.search.router

import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableName
import com.veepee.vpcore.route.link.compose.ComposableParameter
import com.veepee.vpcore.route.link.compose.NoComposableEvent

object SearchScreenName : ComposableName

object SearchScreenLink : ComposableLink<SearchScreenName, NoComposableEvent> {

    override val composableName: SearchScreenName = SearchScreenName

    override val parameter: ComposableParameter? = null
}
