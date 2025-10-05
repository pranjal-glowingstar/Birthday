package com.apps.birthday.repository

import com.apps.birthday.core.common.AppUtils
import com.apps.birthday.data.dao.BirthdayDao
import com.apps.birthday.data.entity.BirthdayEntity
import javax.inject.Inject

class BirthdayRepositoryImpl @Inject constructor(private val birthdayDao: BirthdayDao) :
    IBirthdayRepository {
    override suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity> {
        return birthdayDao.getAllBirthdaysForGivenDate(date, month)
    }

    override suspend fun saveBirthday(birthdayEntity: BirthdayEntity): Long {
        return birthdayDao.saveBirthday(birthdayEntity)
    }

    override suspend fun getUpcomingBirthdaysAfterCurrentMonth(): List<BirthdayEntity> {
        return birthdayDao.getUpcomingBirthdaysAfterCurrentMonth(AppUtils.getCurrentDay(), AppUtils.getCurrentMonth())
    }

    override suspend fun getUpcomingBirthdaysBeforeCurrentMonth(): List<BirthdayEntity> {
        return birthdayDao.getUpcomingBirthdaysBeforeCurrentMonth(AppUtils.getCurrentDay(), AppUtils.getCurrentMonth())
    }

    override suspend fun deleteBirthday(id: String) {
        birthdayDao.deleteBirthday(id)
    }

    override suspend fun getBirthdayById(id: String): BirthdayEntity {
        return birthdayDao.getBirthdayById(id)
    }
}