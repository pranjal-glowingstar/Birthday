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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apps.birthday.presentation.navigation.Routes

@Composable
fun BottomNavBar(navController: NavHostController, navigate: (Routes) -> Unit) {

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route?.let { route ->
        when (route) {
            "com.apps.birthday.presentation.navigation.Routes.Home" -> Routes.Home
            "com.apps.birthday.presentation.navigation.Routes.Add" -> Routes.Add
            "com.apps.birthday.presentation.navigation.Routes.Upcoming" -> Routes.Upcoming
            else -> Routes.Home
        }
    } ?: Routes.Home

    NavigationBar(containerColor = Color.Transparent) {
        tabs.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentDestination,
                onClick = { navigate(item.route) },
                icon = { if (item.route == currentDestination) Icon(item.selectedIcon, "") else Icon(item.unselectedIcon, "") },
                label = { Text(text = item.label) })
        }
    }
}

val tabs = listOf(
    BottomNavModel(Routes.Home, Icons.Outlined.Home, Icons.Filled.Home, "Home"),
    BottomNavModel(Routes.Add, Icons.Outlined.Cake, Icons.Filled.Cake, "Add Date"),
    BottomNavModel(Routes.Upcoming, Icons.Outlined.Upcoming, Icons.Filled.Upcoming, "Upcoming")
)

data class BottomNavModel(
    val route: Routes,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String
)