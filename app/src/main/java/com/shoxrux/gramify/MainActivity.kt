package com.shoxrux.gramify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.shoxrux.gramify.ui.AppNavHost
import com.shoxrux.gramify.ui.theme.GramifyTheme
import com.shoxrux.presentation.screens.auth.signUp.SignUpDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(applicationContext)

        val signUpViewModel: SignUpDataViewModel by viewModels()

        setContent {
            val navController = rememberNavController()


            GramifyTheme {
                AppNavHost(navController, signUpViewModel)
            }
        }
    }
}
