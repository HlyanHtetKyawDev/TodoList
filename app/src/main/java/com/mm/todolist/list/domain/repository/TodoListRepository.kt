package com.mm.todolist.list.domain.repository

import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.network.dto.TodoDto
import com.mm.todolist.list.domain.TodoUI
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {
    suspend fun getTodoListFromNetwork(): List<TodoDto>

    fun getTodoListFromLocal(): Flow<Resource<List<TodoUI>>>
}