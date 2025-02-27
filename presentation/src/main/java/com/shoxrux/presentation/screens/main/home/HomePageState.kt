package com.shoxrux.presentation.screens.main.home

import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.base.UiState

data class HomePageState(
    val isLoading: Boolean,
    val posts: List<PostModel>?,
    val error: String? = null
) : UiState {

    companion object {
        fun initial() = HomePageState(
            isLoading = false,
            posts = null,
            error = null
        )
    }
}
