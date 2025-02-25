package com.shoxrux.presentation.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.screens.main.chat.ChatPage
import com.shoxrux.presentation.screens.main.home.HomePage
import com.shoxrux.presentation.screens.main.post.PostPage
import com.shoxrux.presentation.screens.main.profile.ProfilePage
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shoxrux.presentation.screens.main.reels.ReelsPage

@Composable
fun BottomNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.ITEM_HOME_PAGE,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 250))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 250))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 250))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 250))
        }
    ) {
        composable(route = NavRoutes.ITEM_HOME_PAGE) { HomePage() }
        composable(route = NavRoutes.ITEM_CHAT_PAGE) { ChatPage() }
        composable(route = NavRoutes.ITEM_POST_PAGE) { PostPage() }
        composable(route = NavRoutes.ITEM_REELS_PAGE) { ReelsPage() }
        composable(route = NavRoutes.ITEM_PROFILE_PAGE) { ProfilePage() }
    }


}
