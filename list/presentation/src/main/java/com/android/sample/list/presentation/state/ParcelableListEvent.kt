package com.android.sample.list.presentation.state

import android.os.Parcelable
import com.android.sample.list.abstraction.presentation.ListEvent.OnSubmitQuery
import kotlinx.parcelize.Parcelize

sealed interface ParcelableListEvent : Parcelable {
    @Parcelize
    data class ParcelableOnSubmitEvent(
        override val query: ParcelableMovieQuery
    ) : ParcelableListEvent, OnSubmitQuery
}
