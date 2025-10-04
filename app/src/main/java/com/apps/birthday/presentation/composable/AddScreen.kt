package com.apps.birthday.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apps.birthday.R
import com.apps.birthday.core.analytics.Analytics
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.AddScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun AddScreen(mainViewModel: MainViewModel, addScreenViewModel: AddScreenViewModel) {

    val name by addScreenViewModel.name.collectAsStateWithLifecycle()
    val dob by addScreenViewModel.dob.collectAsStateWithLifecycle()
    val relation by addScreenViewModel.relation.collectAsStateWithLifecycle()
    val message by addScreenViewModel.message.collectAsStateWithLifecycle()

    val onNameChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateName(it)
        }
    }
    val onDobChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateDob(it)
        }
    }
    val onRelationChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateRelation(it)
        }
    }
    val onMessageChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateMessage(it)
        }
    }
    val onSaveClicked: () -> Unit = remember {
        {
            addScreenViewModel.saveBirthday()
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.ADD_SCREEN_LOADED.name)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            Semantic.Padding.VAL_16,
            Alignment.CenterVertically
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Semantic.Padding.VAL_24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.name))
            Spacer(modifier = Modifier.weight(1f))
            BirthdayTextField(name, onNameChange, stringResource(R.string.name_placeholder))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Semantic.Padding.VAL_24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.dob))
            Spacer(modifier = Modifier.weight(1f))
            BirthdayTextField(
                dob, onDobChange, stringResource(R.string.dob_placeholder),
                true, KeyboardType.Number, true
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Semantic.Padding.VAL_24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.relation))
            Spacer(modifier = Modifier.weight(1f))
            BirthdayTextField(
                relation,
                onRelationChange,
                stringResource(R.string.relation_placeholder)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Semantic.Padding.VAL_24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.message))
            Spacer(modifier = Modifier.weight(1f))
            BirthdayTextField(
                message,
                onMessageChange,
                stringResource(R.string.message_placeholder),
                false
            )
        }
        Button(onClick = onSaveClicked) {
            Text(text = stringResource(R.string.submit))
        }
    }
}