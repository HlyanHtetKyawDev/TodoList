package com.mm.todolist.core.data.network.dataSource

import com.mm.todolist.core.data.network.exceptions.EmptyResponseException
import com.mm.todolist.core.data.network.exceptions.FailResponseException
import com.mm.todolist.core.data.network.service.ApiService
import com.mm.todolist.list.data.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.dto.TodoDto
import retrofit2.awaitResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : TodoListNetworkDataSource {

    override suspend fun getTodoList(): List<TodoDto> {
        val response = apiService.getTodoList().awaitResponse()
        if (!response.isSuccessful) {
            throw FailResponseException(response.message())
        }
        if (response.body() == null) {
            throw EmptyResponseException()
        }
        return response.body()!!
    }
}