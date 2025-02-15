package com.shoxrux.presentation.auth.signUp

import androidx.lifecycle.viewModelScope
import com.shoxrux.domain.usecase.AuthUseCase
import com.shoxrux.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : BaseViewModel<SignUpScreenState, SignUpScreenEvent>() {

    private val reducer = SignUpScreenReducer(
        initial = SignUpScreenState.initial(),
        authUseCase = useCase,
        viewModelScope = viewModelScope
    )

    override val state: StateFlow<SignUpScreenState>
        get() = reducer.state

    fun sendEvent(event: SignUpScreenEvent) {
        reducer.sendEvent(event)
    }
}