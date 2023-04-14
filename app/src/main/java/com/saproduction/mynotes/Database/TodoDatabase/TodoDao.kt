package com.saproduction.mynotes.Database.TodoDatabase

import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY uid DESC")
    fun getAll(): MutableList<TodoModel>

    @Insert
    fun insertTodo(todo: TodoModel)

    @Update
    fun updateTodo(todo: TodoModel)


    @Query("DELETE FROM todo_table WHERE uid = (:id)")
    fun deleteTodo(id: Int)

}