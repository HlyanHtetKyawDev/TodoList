package com.mm.todolist.list.data.repository

import android.util.Log
import com.mm.todolist.core.data.helper.getResultOrThrow
import com.mm.todolist.core.data.network.exceptions.GeneralException
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.local.dao.TodoDao
import com.mm.todolist.list.data.mapper.toTodoEntity
import com.mm.todolist.list.data.mapper.toTodoUI
import com.mm.todolist.list.data.network.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.network.dto.TodoDto
import com.mm.todolist.list.domain.TodoUI
import com.mm.todolist.list.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val networkDataSource: TodoListNetworkDataSource,
    private val todoDao: TodoDao
) : TodoListRepository {

    override suspend fun getTodoListFromNetwork(): List<TodoDto> =
        networkDataSource.getTodoList()

    override fun getTodoListFromLocal(): Flow<Resource<List<TodoUI>>> =
        flow {
            emit(Resource.Loading())
            try {
                val data = getResultOrThrow {
                    todoDao.getAll()
                }
                if (data.isNotEmpty()) {
                    emit(Resource.Success(data.map { it.toTodoUI() }))
                } else {
                    val response = getTodoListFromNetwork().map { it.toTodoEntity() }
                    todoDao.upsertAll(response)
                    emit(Resource.Success(response.map { it.toTodoUI() }))
                }
            } catch (e: GeneralException) {
                Log.e("TodoListRepositoryImpl", "sException: ${e.message}")
                emit(Resource.Error(if (e.message.isNullOrEmpty()) "Something went wrong" else e.message!!))
            }
        }
}