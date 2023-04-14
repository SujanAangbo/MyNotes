package com.saproduction.mynotes.Database.SettingsDatabase

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [Settings::class], version = 2)
//abstract class SettingsDatabase : RoomDatabase() {
//
//    abstract fun settingsDao(): SettingsDao
//
//    // Database class after the version update.
//
//
//}

@Database(
    entities = [Settings::class],
    version = 2
)
abstract class SettingsDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    // Database class after the version update.


}

