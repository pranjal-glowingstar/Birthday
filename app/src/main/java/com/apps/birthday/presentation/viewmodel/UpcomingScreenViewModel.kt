package com.apps.birthday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.birthday.core.common.DispatcherProvider
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.repository.IBirthdayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingScreenViewModel @Inject constructor(private val birthdayRepository: IBirthdayRepository): ViewModel(){

    private val _upcomingBirthdays = MutableStateFlow(listOf<BirthdayEntity>())

    val upcomingBirthdays = _upcomingBirthdays.asStateFlow()

    fun getUpcomingBirthdays() {
        viewModelScope.launch(DispatcherProvider.getIoDispatcher()) {
            val upcomingThisYearBirthdays = birthdayRepository.getUpcomingBirthdaysAfterCurrentDate()
            val upcomingNextYearBirthdays = birthdayRepository.getUpcomingBirthdaysBeforeCurrentDate()
            _upcomingBirthdays.value = upcomingThisYearBirthdays + upcomingNextYearBirthdays
        }
    }
    fun deleteBirthday(id: String) {
        viewModelScope.launch(DispatcherProvider.getIoDispatcher()) {
            birthdayRepository.deleteBirthday(id)
            getUpcomingBirthdays()
        }
    }
}