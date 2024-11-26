package com.mm.todolist.list.data.repository

import com.mm.todolist.core.data.helper.getResultOrThrow
import com.mm.todolist.core.data.network.exceptions.GeneralException
import com.mm.todolist.core.data.network.exceptions.NoNetworkException
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.local.dao.TodoDao
import com.mm.todolist.list.data.mapper.toTodoEntity
import com.mm.todolist.list.data.mapper.toTodoUI
import com.mm.todolist.list.data.network.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.domain.TodoUI
import com.mm.todolist.list.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val networkDataSource: TodoListNetworkDataSource,
    private val todoDao: TodoDao
) : TodoListRepository {

    override fun getTodoListFromNetwork(): Flow<Resource<List<TodoUI>>> =
        flow {
            emit(Resource.Loading())
            try {
                val data = getResultOrThrow {
                    networkDataSource.getTodoList()
                }
                if (data.isNotEmpty()) {
                    emit(Resource.Success(data.map { it.toTodoUI() }))
                    todoDao.upsertAll(data.map { it.toTodoEntity() })
                } else {
                    getTodoListFromLocal()
                }
            } catch (e: NoNetworkException) {
                emit(getTodoListFromLocal())
            } catch (e: GeneralException) {
                emit(Resource.Error(if (e.message.isNullOrEmpty()) "Something went wrong" else e.message!!))
            }
        }

    override suspend fun getTodoListFromLocal(): Resource<List<TodoUI>> {
        return try {
            val data = getResultOrThrow {
                todoDao.getAll()
            }
            if (data.isNotEmpty()) {
                Resource.Success(data.map { it.toTodoUI() })
            } else {
                Resource.Error("There is no data")
            }
        } catch (e: GeneralException) {
            Resource.Error(if (e.message.isNullOrEmpty()) "Something went wrong" else e.message!!)
        }
    }
}