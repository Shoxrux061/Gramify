package com.shoxrux.presentation.screens.main.post

import com.shoxrux.presentation.base.Reducer

class PostPageReducer(
    initial: PostPageState
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

            is PostPageEvent.PostEvent -> {
                setState(oldState.copy(isLoading = false, isSuccess = true))
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