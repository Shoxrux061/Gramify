package com.shoxrux.presentation.auth.signIn

import androidx.lifecycle.viewModelScope
import com.shoxrux.domain.usecase.AuthUseCase
import com.shoxrux.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : BaseViewModel<SignInScreenState, SignInScreenEvent>() {

    private val reducer = SignInScreenReducer(
        initial = SignInScreenState.initial(),
        authUseCase = useCase,
        viewModelScope = viewModelScope
    )

    override val state: StateFlow<SignInScreenState>
        get() = reducer.state

    fun sendEvent(event: SignInScreenEvent) {
        reducer.sendEvent(event)
    }

}