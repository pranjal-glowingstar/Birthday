package com.apps.birthday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.birthday.core.common.AppUtils
import com.apps.birthday.core.common.DispatcherProvider
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.repository.IBirthdayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val birthdayRepository: IBirthdayRepository): ViewModel() {

    private val _birthdayList = MutableStateFlow(listOf<BirthdayEntity>())

    val birthdayList = _birthdayList.asStateFlow()

    fun getBirthdayList(){
        viewModelScope.launch(DispatcherProvider.getIoDispatcher()) {
            val day = AppUtils.getCurrentDay()
            val month = AppUtils.getCurrentMonth()
            val birthdays = birthdayRepository.getAllBirthdaysForGivenDate(day, month)
            _birthdayList.value = birthdays
        }
    }
}