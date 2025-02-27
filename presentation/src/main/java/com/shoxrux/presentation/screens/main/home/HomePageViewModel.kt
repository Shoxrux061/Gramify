package com.shoxrux.presentation.screens.main.home

import androidx.lifecycle.viewModelScope
import com.shoxrux.domain.usecase.PostUseCase
import com.shoxrux.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val useCase: PostUseCase
) : BaseViewModel<HomePageState, HomePageEvent>() {

    private val reducer = HomePageReducer(
        initial = HomePageState.initial()
    )

    override val state: StateFlow<HomePageState>
        get() = reducer.state


    fun sendEvent(event: HomePageEvent) {
        reducer.sendEvent(event)


        viewModelScope.launch {
            useCase.getPosts().collect { result ->
                result.data?.let { posts ->
                    reducer.sendEvent(HomePageEvent.HomeEvent(posts))
                } ?: reducer.sendEvent(HomePageEvent.ShowError(result.message))
            }
        }

    }
}
