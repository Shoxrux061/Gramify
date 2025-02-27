package com.shoxrux.presentation.screens.main.home

import androidx.compose.runtime.Immutable
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.base.UiEvent


@Immutable
sealed class HomePageEvent : UiEvent {

    data object Default : HomePageEvent()

    data object LoadingData : HomePageEvent()

    data class HomeEvent(val data: List<PostModel>?) : HomePageEvent()

    data class ShowError(val error: String?) : HomePageEvent()

}