package com.apps.birthday.repository

import com.apps.birthday.data.dao.BirthdayDao
import com.apps.birthday.data.entity.BirthdayEntity
import javax.inject.Inject

class BirthdayRepositoryImpl @Inject constructor(private val birthdayDao: BirthdayDao) :
    IBirthdayRepository {
    override suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity> {
        return birthdayDao.getAllBirthdaysForGivenDate(date, month)
    }
}