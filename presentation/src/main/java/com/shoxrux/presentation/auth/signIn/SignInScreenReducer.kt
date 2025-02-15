package com.shoxrux.presentation.auth.signIn

import com.shoxrux.domain.usecase.AuthUseCase
import com.shoxrux.presentation.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignInScreenReducer(
    initial: SignInScreenState,
    val authUseCase: AuthUseCase,
    val viewModelScope: CoroutineScope
) : Reducer<SignInScreenState, SignInScreenEvent>(initial) {


    override fun reduce(oldState: SignInScreenState, event: SignInScreenEvent) {
        when (event) {
            is SignInScreenEvent.Default -> setState(
                oldState.copy(
                    isSuccess = false,
                    isLoading = false,
                    error = null
                )
            )

            is SignInScreenEvent.SignInEvent -> {
                sendEvent(SignInScreenEvent.LoadingData)
                viewModelScope.launch {
                    try {

                        authUseCase.signInByEmail(email = event.login, password = event.password)
                            .collect {
                                if (it.data != null) {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                } else if (it.message != null) {
                                    sendEvent(SignInScreenEvent.ShowError(it.message))
                                } else {
                                    sendEvent(SignInScreenEvent.LoadingData)
                                }
                            }


                    } catch (e: Exception) {
                        sendEvent(SignInScreenEvent.ShowError(e.message ?: "Unknown error"))
                    }
                }
            }

            is SignInScreenEvent.LoadingData -> {
                setState(oldState.copy(isLoading = true, isSuccess = false))
            }

            is SignInScreenEvent.ShowError -> {
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