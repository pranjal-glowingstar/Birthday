package com.apps.birthday.core.common

import com.apps.birthday.data.entity.BirthdayEntity
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object AppUtils {

    fun getCurrentDay(): Int = LocalDate.now().dayOfMonth
    fun getCurrentMonth(): Int = LocalDate.now().monthValue
    fun getCurrentYear(): Int = LocalDate.now().year
    fun getDateDifference(day: Int, month: Int): Long {
        val today = LocalDate.now()
        val currentYear = today.year

        var nextBirthday = LocalDate.of(currentYear, month, day)

        if (!nextBirthday.isAfter(today)) {
            nextBirthday = nextBirthday.plusYears(1)
        }

        return ChronoUnit.DAYS.between(today, nextBirthday)
    }
    fun getFormattedDate(birthdayEntity: BirthdayEntity): String {
        var result = ""
        result += if (birthdayEntity.dayOfMonth < 10){
            "0${birthdayEntity.dayOfMonth}"
        } else {
            birthdayEntity.dayOfMonth
        }
        result += if (birthdayEntity.monthOfYear < 10){
            "/0${birthdayEntity.monthOfYear}"
        } else {
            "/${birthdayEntity.monthOfYear}"
        }
        return result + "/${birthdayEntity.year}"
    }
}