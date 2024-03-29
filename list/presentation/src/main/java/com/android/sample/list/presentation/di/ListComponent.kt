package com.android.sample.list.presentation.di

import com.android.sample.core.di.component.DaggerComponent
import com.android.sample.core.networking.di.NetworkingModule
import com.android.sample.list.data.di.DataModule
import com.android.sample.list.presentation.ListPresenterFactory
import com.android.sample.list.presentation.ListViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkingModule::class,
        DataModule::class,
        ListModule::class
    ]
)
interface ListComponent : DaggerComponent {

    val viewModelFactory: ListViewModelFactory

    val presenterFactory: ListPresenterFactory

    @Component.Builder
    interface Builder {

        fun build(): ListComponent
    }
}
