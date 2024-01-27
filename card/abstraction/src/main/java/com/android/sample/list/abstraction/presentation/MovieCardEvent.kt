package com.android.sample.list.abstraction.presentation

sealed interface MovieCardEvent {
    interface RequestToReload : MovieCardEvent
}
