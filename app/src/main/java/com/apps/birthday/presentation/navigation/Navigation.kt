package com.apps.birthday.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {

        }
        composable<Routes.Add> {

        }
        composable<Routes.Upcoming> {

        }
    }
}

sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Add : Routes()

    @Serializable
    data object Upcoming : Routes()
}