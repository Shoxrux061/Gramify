package com.shoxrux.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BackgroundColor
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.colors.SemiGray
import com.shoxrux.presentation.ui.colors.TitleTextColor

@Composable
fun MyBottomNavigation(
    navController: NavController
) {

    val listItem = listOf(
        BottomNavigationItem.HomePage,
        BottomNavigationItem.ChatPage,
        BottomNavigationItem.PostPage,
        BottomNavigationItem.ReelsPage,
        BottomNavigationItem.ProfilePage
    )
    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(SemiGray)
        )

        NavigationBar(
            containerColor = BackgroundColor,
            modifier = Modifier.background(BackgroundColor)
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            listItem.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.iconId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BrandColor,
                        selectedTextColor = BrandColor,
                        unselectedIconColor = TitleTextColor,
                        unselectedTextColor = TitleTextColor
                    )
                )
            }
        }
    }
}