package com.shoxrux.presentation.screens.auth.signUp

import androidx.compose.runtime.Immutable
import com.shoxrux.domain.model.user.UserModel
import com.shoxrux.presentation.base.UiEvent


@Immutable
sealed class SignUpScreenEvent : UiEvent {

    data object Default : SignUpScreenEvent()

    data object LoadingData : SignUpScreenEvent()

    data class SignUpEvent(val userModel: UserModel) : SignUpScreenEvent()

    data class ShowError(val errorMessage: String?) : SignUpScreenEvent()

}