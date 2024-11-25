package com.mm.todolist.list.data.mapper

import com.mm.todolist.list.data.local.entitiy.TodoEntity
import com.mm.todolist.list.data.network.dto.TodoDto
import com.mm.todolist.list.domain.TodoUI

fun TodoDto.toTodoEntity() = TodoEntity(
    id = id,
    title = title,
    completed = completed,
    userId = userId
)


fun TodoEntity.toTodoUI() = TodoUI(
    id = id,
    title = title ?: "",
    completed = completed,
    status = if (completed) "Completed" else "Not completed"
)

fun TodoDto.toTodoUI() = TodoUI(
    id = id,
    title = title,
    completed = completed,
    status = if (completed) "Completed" else "Not completed"
)
