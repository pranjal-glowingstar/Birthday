package com.apps.birthday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.core.common.AppUtils
import com.apps.birthday.core.common.DispatcherProvider
import com.apps.birthday.data.entity.BirthdayEntity
import com.apps.birthday.repository.IBirthdayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(private val birthdayRepository: IBirthdayRepository) :
    ViewModel() {

    private val _name = MutableStateFlow(AppConstants.EMPTY_STRING)
    private val _dob = MutableStateFlow(AppConstants.EMPTY_STRING)
    private val _relation = MutableStateFlow(AppConstants.EMPTY_STRING)
    private val _contact = MutableStateFlow(AppConstants.EMPTY_STRING)
    private val _message = MutableStateFlow(AppConstants.EMPTY_STRING)
    private val _error = MutableStateFlow(false)
    private val _saved = MutableStateFlow(false)
    private val _disableSubmit = MutableStateFlow(true)

    val name = _name.asStateFlow()
    val dob = _dob.asStateFlow()
    val relation = _relation.asStateFlow()
    val contact = _contact.asStateFlow()
    val message = _message.asStateFlow()
    val error = _error.asStateFlow()
    val saved = _saved.asStateFlow()
    val disableSubmit = _disableSubmit.asStateFlow()

    fun updateName(value: String) {
        _name.value = value
        checkConstraints()
    }

    fun updateDob(value: String) {
        _dob.value = value.filter { it.isDigit() }.take(8)
        checkConstraints()
    }

    fun updateRelation(value: String) {
        _relation.value = value
        checkConstraints()
    }

    fun updateContact(value: String) {
        _contact.value = value
        checkConstraints()
    }

    fun updateMessage(value: String) {
        _message.value = value
        checkConstraints()
    }
    fun updateError(value: Boolean) {
        _error.value = value
    }

    fun updateSaved(value: Boolean) {
        _saved.value = value
    }

    fun saveBirthday(id: String?) {
        viewModelScope.launch(DispatcherProvider.getIoDispatcher()) {
            val (date, month, year) = getDateMonthYearFromDob()
            if (isValidDob(date, month, year)) {
                val birthday = BirthdayEntity(
                    id ?: UUID.randomUUID().toString(),
                    _name.value,
                    date,
                    month,
                    year,
                    _relation.value,
                    _message.value,
                    _contact.value
                )
                val result = birthdayRepository.saveBirthday(birthday)
                if(result > 0){
                    _saved.value = true
                }else{
                    _saved.value = true
                }
            } else {
                _error.value = true
            }
        }
    }
    private fun checkConstraints(){
        _disableSubmit.value = !(_name.value.isNotEmpty() && _dob.value.length == 8 && _relation.value.isNotEmpty() && _contact.value.isNotEmpty() && _message.value.isNotEmpty())
    }
    fun setExistingEntityDetails(id: String?) {
        id?.let {
            viewModelScope.launch(DispatcherProvider.getIoDispatcher()) {
                val entity = birthdayRepository.getBirthdayById(it)
                _name.value = entity.name
                _dob.value = entity.dayOfMonth.toString()+entity.monthOfYear.toString()+entity.year.toString()
                _relation.value = entity.relation
                _contact.value = entity.contact
                _message.value = entity.message
                checkConstraints()
            }
        }
    }

    private fun getDateMonthYearFromDob(): Triple<Int, Int, Int> {
        return Triple(
            _dob.value.substring(0, 2).toInt(),
            _dob.value.substring(2, 4).toInt(),
            _dob.value.substring(4, 8).toInt()
        )
    }

    private fun isValidDob(date: Int, month: Int, year: Int): Boolean {
        if (year > AppUtils.getCurrentYear()) {
            return false
        }
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> date in 1..31
            4, 6, 9, 11 -> date in 1..30
            2 -> {
                if (isLeapYear(year)) {
                    date in 1..29
                } else {
                    date in 1..28
                }
            }

            else -> false
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return if (year % 4 == 0) {
            if (year % 100 == 0) {
                year % 400 == 0
            } else {
                true
            }
        } else {
            false
        }
    }
}