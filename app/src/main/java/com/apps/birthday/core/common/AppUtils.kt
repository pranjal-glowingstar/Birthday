package com.apps.birthday.core.common

import java.time.LocalDate

object AppUtils {

    fun getCurrentDay(): Int = LocalDate.now().dayOfMonth
    fun getCurrentMonth(): Int = LocalDate.now().monthValue
    fun getCurrentYear(): Int = LocalDate.now().year
}