package com.android.sample.search.presentation.di

import com.android.sample.core.di.DaggerComponent
import com.android.sample.core.networking.di.NetworkingModule
import com.android.sample.search.presentation.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkingModule::class,
        SearchModule::class
    ]
)
internal interface SearchComponent : DaggerComponent {

    val viewModel: SearchViewModel

    @Component.Builder
    interface Builder {

        fun build(): SearchComponent
    }
}
