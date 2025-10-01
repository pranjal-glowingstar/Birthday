package com.apps.birthday.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apps.birthday.data.dao.BirthdayDao
import com.apps.birthday.data.entity.BirthdayEntity

@Database(entities = [BirthdayEntity::class], version = 1)
abstract class BirthdayDatabase: RoomDatabase() {

    abstract fun getBirthdayDao(): BirthdayDao
}