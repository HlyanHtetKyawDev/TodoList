package com.mm.todolist.list.data.dto

data class TodoDto(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)