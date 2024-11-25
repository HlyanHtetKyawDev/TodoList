package com.mm.todolist.list.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mm.todolist.list.data.local.entitiy.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_entity")
    fun getAll(): List<TodoEntity>

    @Upsert(entity = TodoEntity::class)
    suspend fun upsertAll(bookEntity: List<TodoEntity>)

}