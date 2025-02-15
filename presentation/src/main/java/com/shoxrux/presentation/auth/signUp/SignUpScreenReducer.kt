package com.shoxrux.presentation.auth.signUp

import com.shoxrux.domain.usecase.AuthUseCase
import com.shoxrux.presentation.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignUpScreenReducer(
    initial: SignUpScreenState,
    val authUseCase: AuthUseCase,
    val viewModelScope: CoroutineScope
) : Reducer<SignUpScreenState, SignUpScreenEvent>(initial) {


    override fun reduce(oldState: SignUpScreenState, event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.Default -> setState(
                oldState.copy(
                    isSuccess = false,
                    isLoading = false,
                    error = null
                )
            )

            is SignUpScreenEvent.SignUpEvent -> {
                sendEvent(SignUpScreenEvent.LoadingData)
                viewModelScope.launch {
                    try {

                        authUseCase.signUpByEmail(userModel = event.userModel)
                            .collect {
                                if (it.data != null) {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                } else if (it.message != null) {
                                    sendEvent(SignUpScreenEvent.ShowError(it.message))
                                } else {
                                    sendEvent(SignUpScreenEvent.LoadingData)
                                }
                            }


                    } catch (e: Exception) {
                        sendEvent(SignUpScreenEvent.ShowError(e.message ?: "Unknown error"))
                    }
                }
            }

            is SignUpScreenEvent.LoadingData -> {
                setState(oldState.copy(isLoading = true, isSuccess = false))
            }

            is SignUpScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isSuccess = false,
                        isLoading = false,
                        error = event.errorMessage
                    )
                )
            }
        }
    }
}