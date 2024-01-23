package com.android.sample.list.presentation

import androidx.lifecycle.ViewModel
import com.android.sample.list.abstraction.presentation.ListPresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface ListViewModelFactory {
    fun create(
        presenterFactory: ListPresenter
    ): ListViewModel
}

class ListViewModel @AssistedInject constructor(
    @Assisted presenter: ListPresenter
) : ViewModel(), ListPresenter by presenter {

    override fun onCleared() {
        super.onCleared()
        onClear()
    }
}

