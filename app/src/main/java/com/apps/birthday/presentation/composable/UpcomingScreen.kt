package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apps.birthday.R
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.composable.common.BirthdayListItem
import com.apps.birthday.presentation.composable.common.EmptyState
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.MainViewModel
import com.apps.birthday.presentation.viewmodel.UpcomingScreenViewModel

@Composable
fun UpcomingScreen(mainViewModel: MainViewModel, upcomingScreenViewModel: UpcomingScreenViewModel) {

    val upcomingBirthdays by upcomingScreenViewModel.upcomingBirthdays.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.UPCOMING_SCREEN_LOADED.name)
        upcomingScreenViewModel.getUpcomingBirthdays()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Semantic.Padding.VAL_16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Upcoming Birthdays", style = MaterialTheme.typography.titleLarge)
        }
        if (upcomingBirthdays.isNullOrEmpty()) {
            item {
                EmptyState(
                    stringResource(R.string.add_bd_title),
                    stringResource(R.string.add_bd_text)
                )
            }
        } else {
            items(upcomingBirthdays.size) { index ->
                val onEditClicked: (String) -> Unit = remember(upcomingBirthdays[index].id) {
                    {
                        mainViewModel.updateNavigationState(Routes.Add(it))
                    }
                }
                val onDeleteClicked: (String) -> Unit = remember(upcomingBirthdays[index].id) {
                    {
                        upcomingScreenViewModel.deleteBirthday(it)
                    }
                }
                BirthdayListItem(upcomingBirthdays[index], onEditClicked, onDeleteClicked)
            }
        }
    }
}