package com.android.sample.app

import android.app.Application
import com.android.sample.card.ui.MovieCardScreenNameMapper
import com.android.sample.list.ui.ListScreenNameMapper
import com.android.sample.search.presentation.SearchScreenNameMapper
import com.veepee.vpcore.route.GlobalRouterBuilder
import com.veepee.vpcore.route.link.compose.ComposableName
import com.veepee.vpcore.route.link.compose.ComposableNameMapper

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        with(GlobalRouterBuilder) {
            add(MovieCardScreenNameMapper as ComposableNameMapper<out ComposableName>)
            add(ListScreenNameMapper as ComposableNameMapper<out ComposableName>)
            add(SearchScreenNameMapper)
        }
    }
}
