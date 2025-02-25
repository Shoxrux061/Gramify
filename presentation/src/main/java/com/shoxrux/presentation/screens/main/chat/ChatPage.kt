package com.shoxrux.presentation.screens.main.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.shoxrux.presentation.ui.colors.BackgroundColor

@Composable
fun ChatPage() {

    Text(
        modifier = Modifier.fillMaxSize().background(BackgroundColor),
        text = "Chat",
        textAlign = TextAlign.Center
    )

}