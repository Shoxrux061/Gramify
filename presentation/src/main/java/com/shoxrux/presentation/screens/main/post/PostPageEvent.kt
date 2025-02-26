package com.shoxrux.presentation.screens.main.post

import androidx.compose.runtime.Immutable
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.base.UiEvent

@Immutable
sealed class PostPageEvent : UiEvent {

    data object Default : PostPageEvent()

    data object LoadingData : PostPageEvent()

    class PostEvent(val postModel: PostModel) : PostPageEvent()

    class ShowError(val errorMessage: String?) : PostPageEvent()

}