package com.mm.todolist.di

import com.mm.todolist.core.data.network.dataSource.NetworkDataSourceImpl
import com.mm.todolist.list.data.network.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.repository.TodoListRepositoryImpl
import com.mm.todolist.list.domain.repository.TodoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindTodoListNetworkDataSource(
        dataSource: NetworkDataSourceImpl,
    ): TodoListNetworkDataSource

    @Binds
    abstract fun bindTodoListRepository(
        repository: TodoListRepositoryImpl,
    ): TodoListRepository

}