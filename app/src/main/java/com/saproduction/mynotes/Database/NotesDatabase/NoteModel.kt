package com.saproduction.mynotes.Database.NotesDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(true) var id: Int,
    var title: String,
    var description: String,
    var date: String
)
