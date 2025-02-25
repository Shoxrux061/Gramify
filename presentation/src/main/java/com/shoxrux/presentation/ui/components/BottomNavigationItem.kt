package com.shoxrux.presentation.ui.components

import com.shoxrux.core.constants.NavRoutes
import com.shoxrux.presentation.R

sealed class BottomNavigationItem(val title: String, val iconId: Int, val route: String) {

    data object HomePage :
        BottomNavigationItem(
            title = "Home",
            iconId = R.drawable.ic_home,
            route = NavRoutes.ITEM_HOME_PAGE
        )

    data object ChatPage :
        BottomNavigationItem(
            title = "Chat",
            iconId = R.drawable.ic_chat,
            route = NavRoutes.ITEM_CHAT_PAGE
        )

    data object PostPage :
        BottomNavigationItem(
            title = "Post",
            iconId = R.drawable.ic_post,
            route = NavRoutes.ITEM_POST_PAGE
        )

    data object ReelsPage :
        BottomNavigationItem(
            title = "Reels",
            iconId = R.drawable.ic_reels,
            route = NavRoutes.ITEM_REELS_PAGE
        )

    data object ProfilePage :
        BottomNavigationItem(
            title = "Profile",
            iconId = R.drawable.ic_profile,
            route = NavRoutes.ITEM_PROFILE_PAGE
        )

}