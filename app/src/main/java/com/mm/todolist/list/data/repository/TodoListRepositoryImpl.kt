package com.mm.todolist.list.data.repository

import android.util.Log
import com.mm.todolist.core.data.helper.getResultOrThrow
import com.mm.todolist.core.data.network.exceptions.GeneralException
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.mapper.toTodoUI
import com.mm.todolist.list.domain.TodoUI
import com.mm.todolist.list.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val networkDataSource: TodoListNetworkDataSource,
) : TodoListRepository {

    override fun getTodoList(): Flow<Resource<List<TodoUI>>> = flow {
        emit(Resource.Loading())
        try {
            val data = getResultOrThrow {
                networkDataSource.getTodoList()
            }
            if (data.isNotEmpty()) {
                emit(Resource.Success(data.map { it.toTodoUI() }))
            } else {
                emit(
                    Resource.Error(
                        message = "List is empty",
                        errorCode = -1
                    )
                )
            }
        } catch (e: GeneralException) {
            Log.e("TodoListRepositoryImpl", "sException: ${e.message}")
            emit(Resource.Error(if (e.message.isNullOrEmpty()) "Something went wrong" else e.message!!))
        }
    }
}