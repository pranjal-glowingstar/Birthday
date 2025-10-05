package com.apps.birthday.repository

import com.apps.birthday.data.entity.BirthdayEntity

interface IBirthdayRepository {

    suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity>
    suspend fun saveBirthday(birthdayEntity: BirthdayEntity): Long
    suspend fun getUpcomingBirthdaysAfterCurrentMonth(): List<BirthdayEntity>
    suspend fun getUpcomingBirthdaysBeforeCurrentMonth(): List<BirthdayEntity>
}