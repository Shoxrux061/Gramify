package com.shoxrux.presentation.screens.auth.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.BrandSecondary
import com.shoxrux.presentation.ui.components.BigTextField
import com.shoxrux.presentation.ui.components.ButtonWithUnderText
import com.shoxrux.presentation.ui.components.MaskedTextField
import com.shoxrux.presentation.ui.components.TextFieldWithHeader
import com.shoxrux.presentation.ui.trheme.AppTypography

@Composable
fun SignUpScreen(onNext: (SignUpModel) -> Unit, navController: NavController) {

    val options = listOf(
        "Not set",
        "Male",
        "Female",
    )

    val selectedOption = remember { mutableStateOf(options[0]) }
    val expanded = remember { mutableStateOf(false) }

    val isSubmitted = remember { mutableStateOf(false) }

    val email = remember { mutableStateOf("") }
    val bio = remember { mutableStateOf("") }
    val dateOfBirth = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
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
                text = "Personal Information",
                style = AppTypography.headlineMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Please fill the following",
                style = AppTypography.titleMedium
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldWithHeader(
                header = "Full name",
                value = fullName.value,
                onValueChange = {
                    fullName.value = it
                },
                isEmpty = isSubmitted.value && fullName.value.isEmpty()
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldWithHeader(
                header = "Email Address",
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                isEmpty = isSubmitted.value && email.value.isEmpty()

            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {

                Box(modifier = Modifier.weight(1f)) {

                    MaskedTextField(
                        header = "Date of birth",
                        value = dateOfBirth.value,
                        onValueChange = {
                            dateOfBirth.value = it
                        },
                    )

                    Icon(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 15.dp, end = 15.dp),
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "null"
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                Box(modifier = Modifier.weight(1f)) {
                    Column {
                        Text(
                            text = "Gender",
                            style = AppTypography.titleSmall
                        )

                        Card(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .height(55.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = BrandSecondary
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(start = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = selectedOption.value,
                                    style = AppTypography.bodySmall
                                )

                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false }
                                ) {
                                    options.forEach { option ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = option,
                                                    style = AppTypography.bodySmall
                                                )
                                            },
                                            onClick = {
                                                selectedOption.value = option
                                                expanded.value = false
                                            }
                                        )
                                    }
                                }

                                IconButton(
                                    modifier = Modifier
                                        .size(55.dp),
                                    onClick = {
                                        expanded.value = true
                                    }
                                ) {

                                    Icon(
                                        painter = painterResource(R.drawable.ic_arrow_down),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            BigTextField(
                header = "About",
                onValueChange = {
                    bio.value = it
                },
                value = bio.value
            )

            Spacer(modifier = Modifier.weight(1f))

            ButtonWithUnderText(
                onButtonClick = {
                    if (fullName.value.isNotBlank() &&
                        dateOfBirth.value.isNotBlank() &&
                        email.value.isNotBlank()
                    ) {
                        onNext.invoke(
                            SignUpModel(
                                fullName = fullName.value,
                                dateOfBirth = dateOfBirth.value,
                                email = email.value,
                                about = bio.value,
                                gender = options.indexOf(selectedOption.value) // 0 - not set, 1 - male, 2 - female
                            )
                        )
                    } else {
                        isSubmitted.value = true
                    }
                },
                underTextOnClick = {
                    navController.navigate(NavRoutes.SIGN_IN_SCREEN)
                },
                buttonText = "Next",
                underText = "Already have an account?",
                underTextClickable = "Sign in"
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Preview
@Composable
fun PreviewScreen() {

    val navController = rememberNavController()
    SignUpScreen(onNext = {}, navController = navController)

}