package com.shoxrux.presentation.screens.auth.signUp

import android.util.Log
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
        Log.d("Reducer", "Received event: $event") // Проверим, приходит ли событие

        when (event) {
            is SignUpScreenEvent.Default -> {
                Log.d("Reducer", "Handling Default event")
                setState(
                    oldState.copy(
                        isSuccess = false,
                        isLoading = false,
                        error = null
                    )
                )
            }

            is SignUpScreenEvent.SignUpEvent -> {
                Log.d("Reducer", "Handling SignUpEvent")
                sendEvent(SignUpScreenEvent.LoadingData)

                viewModelScope.launch {
                    Log.d("Reducer", "Coroutine launched")
                    try {
                        authUseCase.signUpByEmail(event.userModel).collect { result ->
                            Log.d("Reducer", "Collected result: ${result.data}")

                            when {
                                result.data != null -> {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                    Log.d("Reducer", "Success state set")
                                }
                                result.message != null -> {
                                    sendEvent(SignUpScreenEvent.ShowError(result.message))
                                    Log.d("Reducer", "Error event sent: ${result.message}")
                                }
                                else -> {
                                    sendEvent(SignUpScreenEvent.LoadingData)
                                    Log.d("Reducer", "Loading event sent again")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        sendEvent(SignUpScreenEvent.ShowError(e.message ?: "Unknown error"))
                        Log.d("Reducer", "Exception caught: ${e.message}")
                    }
                }
            }

            is SignUpScreenEvent.LoadingData -> {
                Log.d("Reducer", "Handling LoadingData event")
                setState(oldState.copy(isLoading = true, isSuccess = false))
            }

            is SignUpScreenEvent.ShowError -> {
                Log.d("Reducer", "Handling ShowError event: ${event.errorMessage}")
                setState(
                    oldState.copy(
                        isSuccess = false,
                        isLoading = false,
                        error = event.errorMessage ?: "Unknown error"
                    )
                )
            }
        }
    }

}