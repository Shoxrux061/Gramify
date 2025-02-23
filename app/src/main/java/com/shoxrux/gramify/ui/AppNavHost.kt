package com.shoxrux.gramify.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.screens.auth.signIn.SignInScreen
import com.shoxrux.presentation.screens.auth.signUp.ConfirmPasswordScreen
import com.shoxrux.presentation.screens.auth.signUp.SignUpDataViewModel
import com.shoxrux.presentation.screens.auth.signUp.SignUpScreen
import com.shoxrux.presentation.screens.main.home.MainScreen

@Composable
fun AppNavHost(navController: NavHostController, signUpViewModel: SignUpDataViewModel) {


    NavHost(navController = navController, startDestination = NavRoutes.SIGN_UP_SCREEN) {

        composable(NavRoutes.SIGN_UP_SCREEN) {
            SignUpScreen(
                onNext = { userData ->
                    signUpViewModel.updateUserData(userData)
                    navController.navigate(NavRoutes.CONFIRM_PASSWORD_SCREEN)
                },
                navController
            )
        }

        composable(NavRoutes.SIGN_IN_SCREEN) {
            SignInScreen(navController)
        }

        composable(NavRoutes.MAIN_SCREEN) {
            MainScreen(navController)
        }

        composable(NavRoutes.CONFIRM_PASSWORD_SCREEN) {
            ConfirmPasswordScreen(navController, signUpViewModel)

        }
    }
}