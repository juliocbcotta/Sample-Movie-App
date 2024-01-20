package com.android.sample.list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.sample.card.router.ListScreenLink
import com.android.sample.card.router.ListScreenName
import com.veepee.vpcore.route.link.compose.ComposableEvent
import com.veepee.vpcore.route.link.compose.ComposableLink
import com.veepee.vpcore.route.link.compose.ComposableNameMapper

object ListScreenNameMapper : ComposableNameMapper<ListScreenName> {

    override val supportedNames: Array<ListScreenName> = arrayOf(ListScreenName)

    @Composable
    override fun Map(link: ComposableLink<ListScreenName, ComposableEvent>, modifier: Modifier) {
        when (link) {
            is ListScreenLink -> ListScreenContainer(modifier, link.parameter)
        }
    }
}
