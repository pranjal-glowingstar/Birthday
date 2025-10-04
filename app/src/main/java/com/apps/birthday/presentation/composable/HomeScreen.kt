package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apps.birthday.R
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.HomeScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel) {

    val birthdays by homeScreenViewModel.birthdayList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.HOME_SCREEN_LOADED.name)
        homeScreenViewModel.getBirthdayList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Semantic.Padding.VAL_12, vertical = Semantic.Padding.VAL_16)
    ) {
        Text(text = stringResource(R.string.welcome_text))
        Text(text = stringResource(R.string.remember_text))
        if (birthdays.isEmpty()) {
            Cards()
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_12)
            ) {
                items(birthdays.size) { index ->
                    Cards(birthdays[index])
                }
            }
        }
    }

}