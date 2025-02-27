package com.shoxrux.presentation.screens.main.home

import android.util.Log
import com.shoxrux.presentation.base.Reducer

class HomePageReducer(
    initial: HomePageState
) : Reducer<HomePageState, HomePageEvent>(initial) {

    override fun reduce(oldState: HomePageState, event: HomePageEvent) {
        Log.d("MyReducer", "Event: $event, OldState: $oldState")

        val newState = when (event) {
            is HomePageEvent.Default -> oldState.copy(error = null)
            is HomePageEvent.HomeEvent -> oldState.copy(posts = event.data)
            is HomePageEvent.LoadingData -> oldState.copy(isLoading = true)
            is HomePageEvent.ShowError -> oldState.copy(isLoading = false, error = event.error)
        }

        setState(newState)
    }
}
