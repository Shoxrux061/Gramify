package com.shoxrux.gramify.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.screens.main.MainScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.MAIN_SCREEN,
        enterTransition = { fadeIn(animationSpec = tween(250)) },
        exitTransition = { fadeOut(animationSpec = tween(250)) }
    ) {

        composable(route = NavRoutes.MAIN_SCREEN) {
            MainScreen()
        }
    }
}