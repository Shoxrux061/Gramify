package com.shoxrux.presentation.auth.signIn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun SignInScreen() {

    var login = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

}