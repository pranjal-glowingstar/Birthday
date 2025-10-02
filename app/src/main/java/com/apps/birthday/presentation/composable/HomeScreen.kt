package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {

    LaunchedEffect(Unit) {
        mainViewModel.setCurrentDestination(Routes.Home)
        mainViewModel.triggerEvent(Analytics.EventName.HOME_SCREEN_LOADED.name)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Welcome")
    }

}