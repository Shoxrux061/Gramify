package com.shoxrux.presentation.screens.main.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.components.AppButton

@Preview(showBackground = true)
@Composable
fun HomePage() {

    val viewModel = hiltViewModel<HomePageViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendEvent(HomePageEvent.HomeEvent(null))
    }

    LaunchedEffect(state.value) {

        when {
            state.value.isLoading -> {
                Log.d("TAGSuccess", "PostPage: Loading")

            }

            state.value.posts != null -> {
                viewModel.sendEvent(HomePageEvent.Default)
                Log.d("TAGSuccess", "PostPage: Success ${state.value}")

            }

            state.value.error != null -> {
                Log.d("TAGSuccess", "PostPage: Error")

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(BackgroundColor)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundColor,
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = BrandColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = BrandColor
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Type something...",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = BrandColor.copy(alpha = 0.7f)
                    )

                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Icon(
                painter = painterResource(R.drawable.ic_notification),
                contentDescription = null,
                tint = BrandColor
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow {
            item {
                StoryItem()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val data = state.value.posts

        if (!data.isNullOrEmpty()) {

            LazyColumn {
                items(data.size) {
                    HomePageItem(
                        postTime = data[it].postTime.toString(),
                        imageUrl = data[it].imageUrl,
                        title = data[it].content,
                        likeCount = data[it].likeCount.toString(),
                        commentCount = data[it].commentCount.toString()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}