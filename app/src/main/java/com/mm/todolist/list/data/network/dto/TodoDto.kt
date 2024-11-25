package com.mm.todolist.list.data.network.dto

data class TodoDto(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)