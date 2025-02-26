package com.shoxrux.presentation.screens.main.post

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shoxrux.core.utils.bitmapToByteArray
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.colors.BrandSecondary
import com.shoxrux.presentation.ui.colors.TransParent
import com.shoxrux.presentation.ui.components.AppButton
import com.shoxrux.presentation.ui.components.HeaderWithBackButton
import com.shoxrux.presentation.ui.trheme.AppTypography

@Composable
fun PostPage(navController: NavController) {

    val viewModel = hiltViewModel<PostPageViewModel>()
    val state = viewModel.state.collectAsState()

    val captionText = remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        if (it == null) return@rememberLauncherForActivityResult
        uploadPost(captionText.value, uri = it, navController.context, viewModel)
    }


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        HeaderWithBackButton(
            onBackClick = {},
            header = "Post"
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Select image(s)",
                style = AppTypography.titleSmall
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(180.dp)
                    .clickable {
                        launcher.launch(
                            PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = BrandSecondary
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = BrandColor
                )
            ) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.ic_reels),
                        contentDescription = null
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd),
                        tint = BrandColor,
                        painter = painterResource(R.drawable.ic_post),
                        contentDescription = null
                    )

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            val interactionSource = remember { MutableInteractionSource() }
            val isFocused by interactionSource.collectIsFocusedAsState()


            Text(
                text = "Add caption",
                style = AppTypography.titleSmall
            )
            Card(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(12.dp),
                border = if (isFocused) {
                    BorderStroke(width = 1.dp, color = BrandColor)
                } else {
                    null
                }
            ) {

                TextField(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(BrandSecondary),
                    value = captionText.value,
                    textStyle = AppTypography.bodySmall,
                    onValueChange = {
                        captionText.value = it
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = BrandSecondary,
                        unfocusedContainerColor = BrandSecondary,
                        focusedIndicatorColor = TransParent,
                        unfocusedIndicatorColor = TransParent
                    ),
                    interactionSource = interactionSource
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            AppButton(onClick = {

            }, buttonText = "Upload")

        }
    }
}

fun uploadPost(captionText: String, uri: Uri, context: Context, viewModel: PostPageViewModel) {
    val byteArray = bitmapToByteArray(context, uri)
    viewModel.uploadPost(
        postModel = PostModel(
            imageUrl = uri.toString(),
            content = captionText
        ),
        byteArray = byteArray
    )
}
