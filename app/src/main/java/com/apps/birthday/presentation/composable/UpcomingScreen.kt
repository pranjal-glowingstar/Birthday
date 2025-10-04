package com.apps.birthday.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun UpcomingScreen(mainViewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.UPCOMING_SCREEN_LOADED.name)
    }
}