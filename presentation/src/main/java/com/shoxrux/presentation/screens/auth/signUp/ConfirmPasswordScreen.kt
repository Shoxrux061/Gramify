package com.shoxrux.presentation.screens.auth.signUp

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.domain.model.user.UserModel
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.SemiTransParent
import com.shoxrux.presentation.ui.components.ButtonWithUnderText
import com.shoxrux.presentation.ui.components.TextFieldWithHeader
import com.shoxrux.presentation.ui.trheme.AppTypography

@Composable
fun ConfirmPasswordScreen(navController: NavController, signUpDataViewModel: SignUpDataViewModel) {

    val isSubmitted = remember { mutableStateOf(false) }

    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordConfirm = rememberSaveable { mutableStateOf("") }
    val isDataEntered = remember {
        derivedStateOf {
            username.value.isNotBlank() &&
                    password.value.isNotBlank() &&
                    passwordConfirm.value.isNotBlank()
        }
    }


    val viewModel = hiltViewModel<SignUpViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(state.value) {
        when {
            state.value.isLoading -> {

            }

            state.value.isSuccess -> {
                viewModel.sendEvent(SignUpScreenEvent.Default)
            }

            state.value.error != null -> {

            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
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
                    text = "Select a Username",
                    style = AppTypography.headlineMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Help secure your account",
                    style = AppTypography.titleMedium
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextFieldWithHeader(
                    header = "Username",
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    isEmpty = isSubmitted.value && username.value.isEmpty()

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

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithHeader(
                    header = "Password Confirmation",
                    value = passwordConfirm.value,
                    onValueChange = {
                        passwordConfirm.value = it
                    },
                    isEmpty = isSubmitted.value && passwordConfirm.value.isEmpty()
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonWithUnderText(
                    onButtonClick = {
                        if (isDataEntered.value && passwordConfirm.value == password.value) {
                            val userModel = UserModel(
                                dateOfBirth = signUpDataViewModel.userData.value.dateOfBirth,
                                email = signUpDataViewModel.userData.value.email,
                                password = password.value,
                                fullName = signUpDataViewModel.userData.value.fullName,
                                bio = signUpDataViewModel.userData.value.about,
                                gender = signUpDataViewModel.userData.value.gender

                            )
                            createAccount(userModel, viewModel)
                        } else {
                            isSubmitted.value = true
                        }
                    },
                    underTextOnClick = {
                        /*navController.navigate(NavRoutes.MAIN_SCREEN)*/
                    },
                    buttonText = "Done",
                    underText = "Already have an account?",
                    underTextClickable = "Sign in"
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

fun createAccount(userModel: UserModel, viewModel: SignUpViewModel) {
    viewModel.sendEvent(
        SignUpScreenEvent.SignUpEvent(
            userModel
        )
    )
}


@Preview
@Composable
fun PreviewConfirmScreen() {

    val navController = rememberNavController()

    ConfirmPasswordScreen(
        navController,
        SignUpDataViewModel()
    )
}