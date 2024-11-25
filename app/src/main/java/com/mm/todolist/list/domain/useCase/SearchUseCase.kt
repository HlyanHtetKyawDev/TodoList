package com.mm.todolist.list.domain.useCase

import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.domain.TodoUI
import com.mm.todolist.list.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoListUseCase @Inject constructor(
    private val repository: TodoListRepository
) {
    operator fun invoke(): Flow<Resource<List<TodoUI>>> =
        repository.getTodoList()
}