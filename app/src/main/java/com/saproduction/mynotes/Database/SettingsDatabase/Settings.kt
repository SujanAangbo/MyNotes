package com.saproduction.mynotes.Database.SettingsDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "settings_table")
data class Settings(
    @PrimaryKey @ColumnInfo(name = "setting_name") val firstName: String,
    @ColumnInfo(name = "state") val state: Boolean
)
