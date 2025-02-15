package com.shoxrux.presentation.auth.signIn

import com.shoxrux.presentation.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SignInScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null

) : UiState {

    companion object {
        fun initial() = SignInScreenState(isLoading = false, isSuccess = false, error = null)
    }
}