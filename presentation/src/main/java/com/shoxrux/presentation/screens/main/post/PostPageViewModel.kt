package com.shoxrux.presentation.screens.main.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.domain.usecase.PostUseCase
import com.shoxrux.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostPageViewModel @Inject constructor(
    private val useCase: PostUseCase
) : BaseViewModel<PostPageState, PostPageEvent>() {

    private val reducer = PostPageReducer(
        initial = PostPageState.initial()
    )

    override val state: StateFlow<PostPageState>
        get() = reducer.state

    fun sendEvent(event: PostPageEvent) {
        Log.d("TAGEvent", "sendEvent: $event")
        reducer.sendEvent(event)
    }

    fun uploadPost(postModel: PostModel, byteArray: ByteArray) {

        sendEvent(PostPageEvent.LoadingData)

        viewModelScope.launch {
            try {
                useCase.addPost(postModel, byteArray).collect { result ->
                    Log.d("TAGEvent", "Result: $result")

                    if (result.data != null) {
                        sendEvent(PostPageEvent.PostEvent(postModel = postModel))
                    }/* else {
                        sendEvent(PostPageEvent.ShowError(result.message ?: "Unknown error"))
                    }*/
                }
            } catch (e: Exception) {
                sendEvent(PostPageEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }
}