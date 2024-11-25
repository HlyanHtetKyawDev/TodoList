package com.mm.todolist.list.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mm.todolist.list.data.local.dao.TodoDao
import com.mm.todolist.list.data.local.entitiy.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}