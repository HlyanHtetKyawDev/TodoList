package com.mm.todolist.list.domain.repository

import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.domain.TodoUI
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {
    fun getTodoList(): Flow<Resource<List<TodoUI>>>
}