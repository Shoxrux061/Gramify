package com.shoxrux.gramify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.shoxrux.gramify.ui.AppNavHost
import com.shoxrux.gramify.ui.theme.GramifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(applicationContext)

        setContent {
            val navController = rememberNavController()


            GramifyTheme {
                AppNavHost(navController)
            }
        }
    }
}
