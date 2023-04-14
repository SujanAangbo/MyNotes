package com.saproduction.mynotes.Database.TodoDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoModel(
    @ColumnInfo("title") var title: String,
    @PrimaryKey(true) @ColumnInfo("uid") var uid: Int,
    @ColumnInfo("isChecked") var isChecked: Boolean,
    @ColumnInfo("date") var date: String
)
