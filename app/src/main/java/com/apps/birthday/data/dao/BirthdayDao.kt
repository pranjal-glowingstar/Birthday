package com.apps.birthday.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.birthday.data.entity.BirthdayEntity

@Dao
interface BirthdayDao {

    @Query("select * from birthday where dayOfMonth = :date AND monthOfYear = :month")
    suspend fun getAllBirthdaysForGivenDate(date: Int, month: Int): List<BirthdayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBirthday(birthdayEntity: BirthdayEntity): Long

    @Query("select * from birthday where monthOfYear >= :month OR (monthOfYear = :month AND dayOfMonth > :day) ORDER BY monthOfYear, dayOfMonth")
    suspend fun getUpcomingBirthdaysAfterCurrentMonth(day: Int, month: Int): List<BirthdayEntity>

    @Query("select * from birthday where monthOfYear <= :month OR (monthOfYear = :month AND dayOfMonth < :day) ORDER BY monthOfYear, dayOfMonth")
    suspend fun getUpcomingBirthdaysBeforeCurrentMonth(day: Int, month: Int): List<BirthdayEntity>

    @Query("delete from birthday where id = :id")
    suspend fun deleteBirthday(id: String)

    @Query("select * from birthday where id = :id")
    suspend fun getBirthdayById(id: String): BirthdayEntity

}