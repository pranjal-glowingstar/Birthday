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
class MainViewModel @Inject constructor(): ViewModel() {

    private val _navigation: MutableSharedFlow<Routes> = MutableSharedFlow()
    private val _currentDestination: MutableSharedFlow<Routes> = MutableSharedFlow()
    private val _triggerEvent = MutableSharedFlow<String>()

    val navigation = _navigation.asSharedFlow()
    val currentDestination = _currentDestination.asSharedFlow()
    val triggerEvent = _triggerEvent.asSharedFlow()

    fun updateNavigationState(route: Routes){
        viewModelScope.launch {
            _navigation.emit(route)
        }
    }
    fun setCurrentDestination(route: Routes){
        viewModelScope.launch {
            _currentDestination.emit(route)
        }
    }
    fun triggerEvent(value: String){
        viewModelScope.launch {
            _triggerEvent.emit(value)
        }
    }
}