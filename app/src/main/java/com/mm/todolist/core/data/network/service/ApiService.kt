package com.mm.todolist.core.data.network.service

import com.mm.todolist.core.data.network.EndPoints
import com.mm.todolist.list.data.network.dto.TodoDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(EndPoints.TODOS)
    fun getTodoList(): Call<List<TodoDto>?>
}