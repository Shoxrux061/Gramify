package com.shoxrux.presentation.auth.signIn

import androidx.compose.runtime.Immutable
import com.shoxrux.presentation.base.UiEvent


@Immutable
sealed class SignInScreenEvent : UiEvent{

    data object Default : SignInScreenEvent()

    data object LoadingData : SignInScreenEvent()

    data class SignInEvent(val login: String, val password: String) : SignInScreenEvent()

    data class ShowError(val errorMessage: String?) : SignInScreenEvent()

}