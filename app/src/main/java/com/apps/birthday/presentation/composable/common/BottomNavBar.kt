package com.apps.birthday.presentation.composable.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apps.birthday.presentation.navigation.Routes

@Composable
fun BottomNavBar(navController: NavHostController, navigate: (Routes) -> Unit) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    NavigationBar(containerColor = Color.Transparent) {
        tabs.forEach { item ->
            val selected = when (item.route) {
                is Routes.Home -> destination?.hasRoute<Routes.Home>() == true
                is Routes.Add -> destination?.hasRoute<Routes.Add>() == true
                is Routes.Upcoming -> destination?.hasRoute<Routes.Upcoming>() == true
                is Routes.Error -> destination?.hasRoute<Routes.Error>() == true
            }
            NavigationBarItem(
                selected = selected,
                onClick = { navigate(item.route) },
                icon = {
                    if (selected) Icon(item.selectedIcon, "") else Icon(
                        item.unselectedIcon,
                        ""
                    )
                },
                label = { Text(text = item.label) })
        }
    }
}

val tabs = listOf(
    BottomNavModel(Routes.Home, Icons.Outlined.Home, Icons.Filled.Home, "Home"),
    BottomNavModel(Routes.Add(), Icons.Outlined.Cake, Icons.Filled.Cake, "Add Date"),
    BottomNavModel(Routes.Upcoming, Icons.Outlined.Upcoming, Icons.Filled.Upcoming, "Upcoming")
)

data class BottomNavModel(
    val route: Routes,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String
)