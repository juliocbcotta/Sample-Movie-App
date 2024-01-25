package com.android.sample.card.presentation.di

import com.android.sample.card.data.di.DataModule
import com.android.sample.card.presentation.MovieCardPresenterFactory
import com.android.sample.card.presentation.MovieCardViewModelFactory
import com.android.sample.core.di.component.DaggerComponent
import com.android.sample.core.networking.di.NetworkingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkingModule::class,
        DataModule::class,
        MovieCardModule::class
    ]
)
interface MovieCardComponent : DaggerComponent {

    val viewModelFactory: MovieCardViewModelFactory

    val presenterFactory: MovieCardPresenterFactory

    @Component.Builder
    interface Builder {

        fun build(): MovieCardComponent
    }
}
