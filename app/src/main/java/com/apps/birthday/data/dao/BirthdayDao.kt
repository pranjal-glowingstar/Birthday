package com.apps.birthday.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apps.birthday.data.entity.BirthdayEntity

@Dao
interface BirthdayDao {

    @Query("select * from birthday where dayOfYear = :date AND monthOfYear = :month")
    suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity>
}