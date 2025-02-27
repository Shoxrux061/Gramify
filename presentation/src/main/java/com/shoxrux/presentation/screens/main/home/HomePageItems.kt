package com.shoxrux.presentation.screens.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.Black
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.colors.BrandSecondary
import com.shoxrux.presentation.ui.colors.HeadlineTextColor
import com.shoxrux.presentation.ui.trheme.AppTypography


@Composable
fun HomePageItem(
    imageUrl: String,
    likeCount: String,
    commentCount: String,
    title: String,
    postTime: String,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = BrandSecondary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.person_placholder),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.height(48.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Username",
                        style = AppTypography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = postTime,
                        style = AppTypography.displaySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BrandSecondary
                ),
                border = BorderStroke(
                    color = Black,
                    width = 0.5.dp
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentDescription = "",
                    model = imageUrl,
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    painter = painterResource(R.drawable.ic_heart),
                    tint = Color.Red
                )

                Text(
                    text = "12",
                    style = AppTypography.bodySmall
                )

                Icon(
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .size(20.dp),
                    contentDescription = "",
                    painter = painterResource(R.drawable.ic_chat),
                    tint = HeadlineTextColor
                )

                Text(
                    text = "8",
                    style = AppTypography.bodySmall
                )
            }
        }
    }
}


@Preview
@Composable
fun StoryItem() {

    Box(modifier = Modifier.height(70.dp)) {
        Card(
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape),
            shape = RoundedCornerShape(100),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Magenta
            )
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Icon(
            modifier = Modifier.align(Alignment.BottomEnd),
            painter = painterResource(R.drawable.ic_story_add),
            contentDescription = null,
            tint = BrandColor
        )
    }
}