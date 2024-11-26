package com.mm.todolist.list.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.local.dao.TodoDao
import com.mm.todolist.list.data.local.entitiy.TodoEntity
import com.mm.todolist.list.data.network.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.network.dto.TodoDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class TodoListRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockNetworkDataSource: TodoListNetworkDataSource

    @Mock
    private lateinit var mockTodoDao: TodoDao

    private lateinit var repository: TodoListRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = TodoListRepositoryImpl(mockNetworkDataSource, mockTodoDao)
    }

    /*@Test
    fun `getTodoListFromNetwork returns list of TodoDto`() = runTest {
        val mockTodoDtos = listOf(
            TodoDto(id = 1, title = "Test Todo", userId = 1, completed = false),
            TodoDto(id = 2, title = "Another Todo", userId = 1, completed = true)
        )

        whenever(mockNetworkDataSource.getTodoList()).thenReturn(mockTodoDtos)

        val result = repository.getTodoListFromNetwork()

        assertThat(result).hasSize(2)
        assertThat(result[0].id).isEqualTo(1)
        assertThat(result[1].completed).isTrue()
    }

    @Test
    fun `getTodoListFromNetwork returns empty list`() = runTest {
        val mockTodoDtos = emptyList<TodoDto>()
        whenever(mockNetworkDataSource.getTodoList()).thenReturn(mockTodoDtos)
        val result = repository.getTodoListFromNetwork()
        assertThat(result).hasSize(0)
    }

    @Test
    fun `getTodoListFromLocal returns local data when available`() = runTest {
        val mockTodoEntities = listOf(
            TodoEntity(id = 1, title = "Local Todo", userId = 1, completed = false),
            TodoEntity(id = 2, title = "Another Todo", userId = 1, completed = true)
        )

        whenever(mockTodoDao.getAll()).thenReturn(mockTodoEntities)

        val result = repository.getTodoListFromLocal().toList()
        assertEquals(2, result.size) // Loading + Success
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        val successData = (result[1] as Resource.Success).data
        assertEquals(2, successData?.size)

    }

    @Test
    fun `getTodoListFromLocal fetches from network when local is empty`() = runTest {
        val mockTodoDtos = listOf(
            TodoDto(id = 1, title = "Network Todo", userId = 1, completed = true)
        )
        whenever(mockTodoDao.getAll()).thenReturn(emptyList())
        whenever(mockNetworkDataSource.getTodoList()).thenReturn(mockTodoDtos)

        val result = repository.getTodoListFromLocal().toList()
        assertEquals(2, result.size) // Loading + Success
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        val successData = (result[1] as Resource.Success).data
        assertEquals(1, successData?.size)
    }*/
}