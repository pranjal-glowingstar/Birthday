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
import androidx.compose.runtime.DisposableEffect
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
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.presentation.composable.common.BirthdayTextField
import com.apps.birthday.presentation.navigation.Routes
import com.apps.birthday.presentation.semantics.Semantic
import com.apps.birthday.presentation.viewmodel.AddScreenViewModel
import com.apps.birthday.presentation.viewmodel.MainViewModel

@Composable
fun AddScreen(mainViewModel: MainViewModel, addScreenViewModel: AddScreenViewModel, id: String?) {

    val name by addScreenViewModel.name.collectAsStateWithLifecycle()
    val dob by addScreenViewModel.dob.collectAsStateWithLifecycle()
    val relation by addScreenViewModel.relation.collectAsStateWithLifecycle()
    val contact by addScreenViewModel.contact.collectAsStateWithLifecycle()
    val message by addScreenViewModel.message.collectAsStateWithLifecycle()
    val saved by addScreenViewModel.saved.collectAsStateWithLifecycle()
    val disableSubmit by addScreenViewModel.disableSubmit.collectAsStateWithLifecycle()

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
    val onContactChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateContact(it)
        }
    }
    val onMessageChange: (String) -> Unit = remember {
        {
            addScreenViewModel.updateMessage(it)
        }
    }
    val onSaveClicked: () -> Unit = remember {
        {
            addScreenViewModel.saveBirthday(id)
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.triggerEvent(Analytics.EventName.ADD_SCREEN_LOADED.name)
        addScreenViewModel.setExistingEntityDetails(id)
    }

    LaunchedEffect(saved) {
        if (saved) {
            mainViewModel.updateNavigationState(Routes.Home)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addScreenViewModel.updateDob(AppConstants.EMPTY_STRING)
            addScreenViewModel.updateName(AppConstants.EMPTY_STRING)
            addScreenViewModel.updateMessage(AppConstants.EMPTY_STRING)
            addScreenViewModel.updateRelation(AppConstants.EMPTY_STRING)
            addScreenViewModel.updateContact(AppConstants.EMPTY_STRING)
            addScreenViewModel.updateError(false)
            addScreenViewModel.updateSaved(false)
        }
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
            Text(text = stringResource(R.string.contact))
            Spacer(modifier = Modifier.weight(1f))
            BirthdayTextField(
                contact,
                onContactChange,
                stringResource(R.string.contact_placeholder)
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
        Button(onClick = onSaveClicked, enabled = !disableSubmit) {
            Text(text = stringResource(R.string.submit))
        }
    }
}