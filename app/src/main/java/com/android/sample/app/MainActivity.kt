package com.android.sample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.sample.core.ui.theme.MyApplicationTheme
import com.android.sample.search.router.SearchScreenLink
import com.veepee.vpcore.route.link.compose.ComposableFor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                ComposableFor(SearchScreenLink)
            }
        }
    }
}
