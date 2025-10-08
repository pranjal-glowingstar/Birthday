package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.apps.birthday.R
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun ErrorScreen(viewModel: MainViewModel, errorCode: String, errorMessage: String?) {

    val onClick = remember(viewModel) {
        {
            viewModel.navigateToSettingsIfRequired()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = Semantic.Padding.VAL_16,
            Alignment.CenterVertically
        )
    ) {
        Text(
            text = when (errorCode) {
                AppConstants.ALARM_PERMISSION -> stringResource(R.string.alarm_permission_title)
                else -> stringResource(R.string.something_went_wrong)
            },
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = errorMessage ?: stringResource(R.string.unable_to_proceed),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        ElevatedButton(onClick = onClick) {
            Text(text = stringResource(R.string.goto_settings))
        }
    }
}