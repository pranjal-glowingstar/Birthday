package com.apps.birthday.repository

import com.apps.birthday.data.entity.BirthdayEntity

interface IBirthdayRepository {

    suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity>
}