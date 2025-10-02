package com.apps.birthday.core.di

import android.content.Context
import androidx.room.Room
import com.apps.birthday.core.common.AppConstants
import com.apps.birthday.data.BirthdayDatabase
import com.apps.birthday.data.dao.BirthdayDao
import com.apps.birthday.repository.BirthdayRepositoryImpl
import com.apps.birthday.repository.IBirthdayRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BirthdayModule {

    @Provides
    @Singleton
    fun provideBirthdayDatabase(@ApplicationContext context: Context): BirthdayDatabase {
        return Room.databaseBuilder(
            context,
            BirthdayDatabase::class.java,
            AppConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBirthdayDao(database: BirthdayDatabase): BirthdayDao {
        return database.getBirthdayDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BirthdayBindModule {

    @Binds
    @Singleton
    abstract fun getBirthdayRepository(birthdayRepositoryImpl: BirthdayRepositoryImpl): IBirthdayRepository

}