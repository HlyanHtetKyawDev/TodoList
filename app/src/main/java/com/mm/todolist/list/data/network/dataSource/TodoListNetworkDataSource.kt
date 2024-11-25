package com.mm.todolist.list.data.network.dataSource

import com.mm.todolist.list.data.network.dto.TodoDto

interface TodoListNetworkDataSource {
    suspend fun getTodoList(): List<TodoDto>
}