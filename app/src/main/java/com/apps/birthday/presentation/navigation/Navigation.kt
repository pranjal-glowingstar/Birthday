package com.apps.birthday.presentation.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apps.birthday.presentation.composable.AddScreen
import com.apps.birthday.presentation.composable.BottomNavBar
import com.apps.birthday.presentation.composable.HomeScreen
import com.apps.birthday.presentation.composable.UpcomingScreen
import com.apps.birthday.presentation.viewmodel.AddScreenViewModel
import com.apps.birthday.presentation.viewmodel.HomeScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.serialization.Serializable

@Composable
fun NavigationComponent(viewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel, addScreenViewModel: AddScreenViewModel) {

    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.navigation.filter { it != null }.collect { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    val navigate: (Routes) -> Unit = remember(viewModel) {
        {
            viewModel.updateNavigationState(it)
        }
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        bottomBar = { BottomNavBar(navController, navigate) }) { _ ->
        Navigation(navController, viewModel, homeScreenViewModel, addScreenViewModel)
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel, addScreenViewModel: AddScreenViewModel) {
    NavHost(navController = navController, startDestination = Routes.Add) {
        composable<Routes.Home> {
            HomeScreen(viewModel, homeScreenViewModel)
        }
        composable<Routes.Add> {
            AddScreen(viewModel, addScreenViewModel)
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