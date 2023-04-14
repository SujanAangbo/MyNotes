package com.saproduction.mynotes.Database.SettingsDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Query("SELECT state FROM settings_table WHERE setting_name = :name")
    fun getStatusOf(name: String): Boolean

    @Query("SELECT setting_name FROM settings_table")
    fun getItems(): List<String>

    @Query("UPDATE settings_table SET state = (:state) WHERE setting_name = (:name)")
    fun updateStatusOf(name: String, state: Boolean)

    @Insert
    fun insertAll(users: List<Settings>)

//    @Delete
//    fun delete(user: User)
}