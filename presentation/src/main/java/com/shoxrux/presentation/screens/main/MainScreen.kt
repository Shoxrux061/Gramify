package com.shoxrux.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.components.BottomNavHost
import com.shoxrux.presentation.ui.components.MyBottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.background(BackgroundColor),
        bottomBar = {
            MyBottomNavigation(
                navController = navController
            )
        }) {

        BottomNavHost(navController)
    }
}