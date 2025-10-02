package com.apps.birthday.presentation.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apps.birthday.presentation.composable.AddScreen
import com.apps.birthday.presentation.composable.BottomNavBar
import com.apps.birthday.presentation.composable.HomeScreen
import com.apps.birthday.presentation.composable.UpcomingScreen
import com.apps.birthday.presentation.viewmodel.HomeScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Composable
fun NavigationComponent(viewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel) {
    val navController = rememberNavController()
    val performNavigation by viewModel.navigation.collectAsStateWithLifecycle(initialValue = Routes.Home)
    val currentDestination by viewModel.currentDestination.collectAsStateWithLifecycle(initialValue = Routes.Home)

    LaunchedEffect(performNavigation) {
        navController.navigate(performNavigation){
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigate: (Routes) -> Unit = remember(viewModel) {
        {
            viewModel.updateNavigationState(it)
        }
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        bottomBar = { BottomNavBar(currentDestination, navigate) }) { _ ->
        Navigation(navController, viewModel, homeScreenViewModel)
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel) {
    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            HomeScreen(viewModel, homeScreenViewModel)
        }
        composable<Routes.Add> {
            AddScreen(viewModel)
        }
        composable<Routes.Upcoming> {
            UpcomingScreen(viewModel)
        }
    }
}

@Serializable
sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Add : Routes()

    @Serializable
    data object Upcoming : Routes()
}