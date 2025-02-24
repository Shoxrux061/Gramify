package com.shoxrux.gramify.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shoxrux.core.constants.NavRoutes

@Composable
fun AppNavHost(navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavRoutes.SIGN_UP_SCREEN) {

    }
}