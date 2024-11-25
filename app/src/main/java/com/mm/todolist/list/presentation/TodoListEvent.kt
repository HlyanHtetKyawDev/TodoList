package com.mm.todolist.list.presentation

import com.mm.todolist.core.data.network.utils.GeneralError

sealed class TodoListEvent {
    data class Error(val error: GeneralError) : TodoListEvent()
}