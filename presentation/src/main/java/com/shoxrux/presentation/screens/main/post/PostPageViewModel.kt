package com.shoxrux.presentation.screens.main.post

import androidx.lifecycle.viewModelScope
import com.shoxrux.domain.usecase.PostUseCase
import com.shoxrux.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PostPageViewModel @Inject constructor(
    private val useCase: PostUseCase
) : BaseViewModel<PostPageState, PostPageEvent>() {

    private val reducer = PostPageReducer(
        initial = PostPageState.initial(),
        postUseCase = useCase,
        viewModelScope = viewModelScope
    )

    override val state: StateFlow<PostPageState>
        get() = reducer.state

    fun sendEvent(event: PostPageEvent) {
        reducer.sendEvent(event)
    }

}