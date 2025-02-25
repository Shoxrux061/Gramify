package com.shoxrux.presentation.screens.main.post

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoxrux.domain.model.post.PostModel

@Composable
fun PostPage() {

    val viewModel = hiltViewModel<PostPageViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(state.value) {

        when {
            state.value.isLoading -> {
                Log.d("TAGSuccess", "PostPage: Loading")

            }

            state.value.isSuccess -> {
                viewModel.sendEvent(PostPageEvent.Default)
                Log.d("TAGSuccess", "PostPage: Success")

            }

            state.value.error != null -> {
                Log.d("TAGSuccess", "PostPage: Error")

            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Post",
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                viewModel.sendEvent(
                    PostPageEvent.SignInEvent(
                        postModel = PostModel(
                            authorId = "asdasdasd",
                            content = "My Post",
                            imageUrl = "adasd",
                            likeCount = 2,
                            commentCount = 2
                        )
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add Post"
            )
        }
    }
}