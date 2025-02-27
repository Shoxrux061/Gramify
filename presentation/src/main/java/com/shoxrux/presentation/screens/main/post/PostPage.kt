package com.shoxrux.presentation.screens.main.post

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.shoxrux.core.utils.bitmapToByteArray
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.colors.BrandSecondary
import com.shoxrux.presentation.ui.colors.SemiGray
import com.shoxrux.presentation.ui.colors.TransParent
import com.shoxrux.presentation.ui.components.AppButton
import com.shoxrux.presentation.ui.trheme.AppTypography

@Composable
fun PostPage(navController: NavController) {

    val viewModel = hiltViewModel<PostPageViewModel>()
    val state = viewModel.state.collectAsState()

    val isSubmitted = remember { mutableStateOf(false) }

    val captionText = rememberSaveable { mutableStateOf("") }
    val selectedImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }


    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        if (it == null) return@rememberLauncherForActivityResult
        selectedImageUri.value = it
    }


    LaunchedEffect(state.value) {

        when {
            state.value.isLoading -> {
                Log.d("TAGSuccess", "PostPage: Loading")
            }

            state.value.isSuccess -> {
                Toast.makeText(navController.context, "Loaded", Toast.LENGTH_SHORT).show()
                viewModel.sendEvent(PostPageEvent.Default)
                selectedImageUri.value = null
                captionText.value = ""
                Log.d("TAGSuccess", "PostPage: Success")
            }

            state.value.error != null -> {
                Toast.makeText(navController.context, "Canceled", Toast.LENGTH_SHORT).show()
                Log.d("TAGSuccess", "PostPage: Error")
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Post",
                style = AppTypography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

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
                border = if (isSubmitted.value && selectedImageUri.value == null) {
                    BorderStroke(
                        width = 1.dp,
                        color = Color.Red
                    )
                } else {
                    BorderStroke(
                        width = 1.dp,
                        color = BrandColor
                    )
                }
            ) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(selectedImageUri.value),
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
                } else if (captionText.value.isBlank() && isSubmitted.value) {
                    BorderStroke(width = 1.dp, color = Color.Red)
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
                if (selectedImageUri.value != null && captionText.value.isNotEmpty()) {
                    uploadPost(
                        captionText.value,
                        uri = selectedImageUri.value!!,
                        navController.context,
                        viewModel
                    )
                } else {
                    isSubmitted.value = true
                }

            }, buttonText = "Upload")

        }

        if (state.value.isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SemiGray)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

fun uploadPost(captionText: String, uri: Uri, context: Context, viewModel: PostPageViewModel) {
    val byteArray = bitmapToByteArray(context, uri)
    viewModel.uploadPost(
        postModel = PostModel(
            content = captionText
        ),
        byteArray = byteArray
    )
}