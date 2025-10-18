package com.apps.birthday.repository

import com.apps.birthday.data.entity.BirthdayEntity

interface IBirthdayRepository {

    suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity>
    suspend fun saveBirthday(birthdayEntity: BirthdayEntity): Long
    suspend fun getUpcomingBirthdaysAfterCurrentDate(): List<BirthdayEntity>
    suspend fun getUpcomingBirthdaysBeforeCurrentDate(): List<BirthdayEntity>
    suspend fun deleteBirthday(id: String)
    suspend fun getBirthdayById(id: String): BirthdayEntity
}