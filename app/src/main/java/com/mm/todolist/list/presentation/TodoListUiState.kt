package com.mm.todolist.list.presentation

import com.mm.todolist.core.data.network.utils.GeneralError
import com.mm.todolist.list.domain.TodoUI

data class TodoListUiState(
    val isLoading: Boolean = false,
    val todoList: List<TodoUI> = emptyList(),
    val error: GeneralError? = null,
)
