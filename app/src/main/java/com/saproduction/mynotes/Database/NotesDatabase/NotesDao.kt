package com.saproduction.mynotes.Database.NotesDatabase

import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAll(): MutableList<NoteModel>

    @Insert
    fun insert(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Query("DELETE FROM notes_table WHERE id IN (:id)")
    fun deleteById(id: Int)

}