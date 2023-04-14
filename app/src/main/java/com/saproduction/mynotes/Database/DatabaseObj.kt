package com.saproduction.mynotes.Database

import android.content.Context
import androidx.room.Room
import com.saproduction.mynotes.Database.NotesDatabase.NotesDatabase
import com.saproduction.mynotes.Database.SettingsDatabase.SettingsDatabase
import com.saproduction.mynotes.Database.TodoDatabase.TodoDatabase

object DatabaseObj {

    // For notes database
    fun getNotesDatabase(context: Context): NotesDatabase {

        return Room.databaseBuilder(
            context, NotesDatabase::class.java, "notes_db"
        ).build()
    }

    // For todo database
    fun getTodoDatabase(context: Context): TodoDatabase {

        return Room.databaseBuilder(
            context, TodoDatabase::class.java, "todo_table"
        ).build()
    }


    // For settings database
    fun getSettingsDatabase(context: Context): SettingsDatabase {

        return Room.databaseBuilder(context, SettingsDatabase::class.java, "settings_table")
            .build()

    }


}