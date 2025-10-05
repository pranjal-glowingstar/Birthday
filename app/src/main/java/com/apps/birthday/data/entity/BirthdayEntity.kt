package com.apps.birthday.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.birthday.core.common.AppConstants
import kotlinx.serialization.Serializable

@Entity(tableName = "birthday")
@Serializable
data class BirthdayEntity(
    @ColumnInfo("id") @PrimaryKey val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("dayOfMonth") val dayOfMonth: Int,
    @ColumnInfo("monthOfYear") val monthOfYear: Int,
    @ColumnInfo("year") val year: Int,
    @ColumnInfo("relation") val relation: String,
    @ColumnInfo("message") val message: String = AppConstants.EMPTY_STRING,
    @ColumnInfo("contact") val contact: String
)
