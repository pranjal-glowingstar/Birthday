package com.apps.birthday.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun AddScreen(mainViewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        mainViewModel.setCurrentDestination(Routes.Add)
        mainViewModel.triggerEvent(Analytics.EventName.ADD_SCREEN_LOADED.name)
    }
}