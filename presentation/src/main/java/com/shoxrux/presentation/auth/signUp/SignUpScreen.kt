package com.shoxrux.presentation.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoxrux.domain.model.user.UserModel


@Preview(showBackground = true)
@Composable
fun SignUpScreen() {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val bio = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }

    val viewModel = hiltViewModel<SignUpViewModel>()
    val state = viewModel.state.collectAsState()

    when {
        state.value.isLoading -> {

        }

        state.value.isSuccess -> {
            viewModel.sendEvent(SignUpScreenEvent.Default)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            viewModel.sendEvent(
                SignUpScreenEvent.SignUpEvent(
                    userModel = UserModel(
                        email = "shoxruxurmanov2@gmail.com",
                        password = "Aa86062803"
                    )
                )
            )
        }) {

        }
    }

}