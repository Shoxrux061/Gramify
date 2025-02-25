package com.shoxrux.presentation.screens.main.post

import com.shoxrux.domain.usecase.PostUseCase
import com.shoxrux.presentation.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PostPageReducer(
    initial: PostPageState,
    val postUseCase: PostUseCase,
    val viewModelScope: CoroutineScope
) : Reducer<PostPageState, PostPageEvent>(initial) {


    override fun reduce(oldState: PostPageState, event: PostPageEvent) {
        when (event) {
            is PostPageEvent.Default -> setState(
                oldState.copy(
                    isSuccess = false,
                    isLoading = false,
                    error = null
                )
            )

            is PostPageEvent.SignInEvent -> {
                sendEvent(PostPageEvent.LoadingData)
                viewModelScope.launch {
                    try {

                        postUseCase.addPost(event.postModel).collect {
                            if (it.data != null) {
                                setState(oldState.copy(isLoading = false, isSuccess = true))
                            } else if (it.message != null) {
                                sendEvent(PostPageEvent.ShowError(it.message))
                            } else {
                                sendEvent(PostPageEvent.LoadingData)
                            }
                        }


                    } catch (e: Exception) {
                        sendEvent(PostPageEvent.ShowError(e.message ?: "Unknown error"))
                    }
                }
            }

            is PostPageEvent.LoadingData -> {
                setState(oldState.copy(isLoading = true, isSuccess = false))
            }

            is PostPageEvent.ShowError -> {
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