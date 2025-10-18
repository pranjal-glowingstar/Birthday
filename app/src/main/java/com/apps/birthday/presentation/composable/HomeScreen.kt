package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apps.birthday.R
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.composable.common.Cards
import com.apps.birthday.presentation.composable.common.EmptyState
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.HomeScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel, homeScreenViewModel: HomeScreenViewModel) {

    val birthdays by homeScreenViewModel.birthdayList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.HOME_SCREEN_LOADED.name)
        mainViewModel.navigateToSettingsIfRequired()
        homeScreenViewModel.getBirthdayList()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Semantic.Padding.VAL_24, vertical = Semantic.Padding.VAL_16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderSection()

            Spacer(modifier = Modifier.height(Semantic.Padding.VAL_24))

            if (birthdays.isEmpty()) {
                EmptyState(stringResource(R.string.no_bd_title), stringResource(R.string.no_bd_subtitle))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Semantic.Padding.VAL_12)
                ) {
                    items(
                        items = birthdays,
                        key = { it.id }
                    ) { birthday ->
                        Cards(birthday)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Semantic.Padding.VAL_4))
        Text(
            text = stringResource(R.string.remember_text),
            style = MaterialTheme.typography.titleMedium
        )
    }
}