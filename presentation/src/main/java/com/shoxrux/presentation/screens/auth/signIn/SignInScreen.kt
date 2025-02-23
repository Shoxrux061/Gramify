package com.shoxrux.presentation.screens.auth.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.R
import com.shoxrux.presentation.screens.auth.signUp.SignUpScreenEvent
import com.shoxrux.presentation.screens.auth.signUp.SignUpViewModel
import com.shoxrux.presentation.ui.colors.SemiTransParent
import com.shoxrux.presentation.ui.components.ButtonWithUnderText
import com.shoxrux.presentation.ui.components.TextFieldWithHeader
import com.shoxrux.presentation.ui.trheme.AppTypography


@Composable
fun SignInScreen(navController: NavController) {

    val isSubmitted = remember { mutableStateOf(false) }

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val viewModel = hiltViewModel<SignInViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(state.value) {

        when {
            state.value.isLoading -> {

            }

            state.value.isSuccess -> {
                viewModel.sendEvent(SignInScreenEvent.Default)
                navController.navigate(NavRoutes.MAIN_SCREEN)
            }

            state.value.error != null -> {

            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = "back button"
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Text(
                    text = "Sign In",
                    style = AppTypography.headlineMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Enter your credentials",
                    style = AppTypography.titleMedium
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldWithHeader(
                    header = "Email Adress",
                    value = login.value,
                    onValueChange = {
                        login.value = it
                    },
                    isEmpty = isSubmitted.value && login.value.isEmpty()

                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithHeader(
                    header = "Password",
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    isEmpty = isSubmitted.value && password.value.isEmpty()
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonWithUnderText(
                    onButtonClick = {
                        if (login.value.isNotBlank() && password.value.isNotBlank()) {
                            viewModel.sendEvent(
                                SignInScreenEvent.SignInEvent(
                                    login = login.value,
                                    password = password.value
                                )
                            )
                        } else {
                            isSubmitted.value = true
                        }
                    },
                    buttonText = "Login",
                    underText = "Do not have an account?",
                    underTextClickable = "Sign Up",
                    underTextOnClick = {
                        navController.navigate(NavRoutes.SIGN_UP_SCREEN)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

            }
        }

        if (state.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        SemiTransParent
                    )
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController()
    SignInScreen(navController)
}