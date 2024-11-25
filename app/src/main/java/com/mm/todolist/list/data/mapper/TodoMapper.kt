package com.mm.todolist.list.data.mapper

import com.mm.todolist.list.data.network.dto.TodoDto
import com.mm.todolist.list.domain.TodoUI

fun TodoDto.toTodoUI() = TodoUI(
    id = id,
    title = title,
    completed = completed,
    status = if (completed) "Completed" else "Not completed"
)
