package com.shoxrux.presentation.screens.main.post

import androidx.compose.runtime.Immutable
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.base.UiEvent


@Immutable
sealed class PostPageEvent : UiEvent {

    data object Default : PostPageEvent()

    data object LoadingData : PostPageEvent()

    data class SignInEvent(val postModel: PostModel) : PostPageEvent()

    data class ShowError(val errorMessage: String?) : PostPageEvent()

}