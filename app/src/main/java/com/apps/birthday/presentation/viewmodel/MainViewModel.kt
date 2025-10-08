package com.apps.birthday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.birthday.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _navigation = MutableSharedFlow<Routes>()
    private val _triggerEvent = MutableSharedFlow<String>()
    private val _settings = MutableSharedFlow<Unit>()

    val navigation = _navigation.asSharedFlow()
    val triggerEvent = _triggerEvent.asSharedFlow()
    val settings = _settings.asSharedFlow()

    fun updateNavigationState(route: Routes) {
        viewModelScope.launch {
            _navigation.emit(route)
        }
    }

    fun triggerEvent(value: String) {
        viewModelScope.launch {
            _triggerEvent.emit(value)
        }
    }

    fun navigateToSettingsIfRequired() {
        viewModelScope.launch {
            _settings.emit(Unit)
        }
    }
}