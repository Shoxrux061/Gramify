package com.shoxrux.presentation.screens.main.post

import com.shoxrux.presentation.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class PostPageState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null

) : UiState {

    companion object {
        fun initial() = PostPageState(isLoading = false, isSuccess = false, error = null)
    }
}