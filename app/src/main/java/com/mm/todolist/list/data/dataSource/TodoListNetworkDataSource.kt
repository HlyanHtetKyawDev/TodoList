package com.mm.todolist.list.data.dataSource

import com.mm.todolist.list.data.dto.TodoDto

interface TodoListNetworkDataSource {
    suspend fun getTodoList(): List<TodoDto>
}