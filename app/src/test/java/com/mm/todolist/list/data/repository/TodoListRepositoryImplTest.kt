package com.mm.todolist.list.data.repository

import com.mm.todolist.core.data.network.exceptions.GeneralException
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.list.data.local.dao.TodoDao
import com.mm.todolist.list.data.local.entitiy.TodoEntity
import com.mm.todolist.list.data.mapper.toTodoEntity
import com.mm.todolist.list.data.mapper.toTodoUI
import com.mm.todolist.list.data.network.dataSource.TodoListNetworkDataSource
import com.mm.todolist.list.data.network.dto.TodoDto
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class TodoListRepositoryImplTest {

    @Mock
    private lateinit var mockNetworkDataSource: TodoListNetworkDataSource

    @Mock
    private lateinit var mockTodoDao: TodoDao

    private lateinit var repository: TodoListRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = TodoListRepositoryImpl(mockNetworkDataSource, mockTodoDao)
    }

    @Test
    fun `getTodoListFromNetwork should return success with data when network call succeeds`() =
        runTest {
            // Arrange
            val mockNetworkData = listOf(
                TodoDto(false, 1, "Test 1", 1),
                TodoDto(false, 2, "Test 2", 1)
            )
            val mockUiData = mockNetworkData.map { it.toTodoUI() }
            val mockEntityData = mockNetworkData.map { it.toTodoEntity() }

            whenever(mockNetworkDataSource.getTodoList()).thenReturn(mockNetworkData)

            // Act
            val results = repository.getTodoListFromNetwork().toList()

            // Assert
            assert(results[0] is Resource.Loading)
            assert(results[1] is Resource.Success)
            assertEquals(mockUiData, (results[1] as Resource.Success).data)

            // Verify that the transformed data is saved locally
            Mockito.verify(mockTodoDao).upsertAll(mockEntityData)
        }

    @Test
    fun `getTodoListFromNetwork should emit error on GeneralException`() = runTest {
        // Arrange
        val errorMessage = "General error occurred"
        whenever(mockNetworkDataSource.getTodoList()).thenThrow(GeneralException(errorMessage))

        // Act
        val results = repository.getTodoListFromNetwork().toList()

        // Assert
        assert(results[0] is Resource.Loading)
        assert(results[1] is Resource.Error)
        assertEquals(errorMessage, (results[1] as Resource.Error).message)
    }

    @Test
    fun `getTodoListFromLocal should return success with data when local data exists`() =
        runTest {
            // Arrange
            val mockLocalData = listOf(
                TodoEntity(1, 1, "Local Test 1", false),
                TodoEntity(2, 1, "Local Test 2", true)
            )
            whenever(mockTodoDao.getAll()).thenReturn(mockLocalData)

            // Act
            val result = repository.getTodoListFromLocal()

            // Assert
            assert(result is Resource.Success)
            assertEquals(mockLocalData.map { it.toTodoUI() }, (result as Resource.Success).data)
        }

    @Test
    fun `getTodoListFromLocal should return error when local data is empty`() = runTest {
        // Arrange
        whenever(mockTodoDao.getAll()).thenReturn(emptyList())

        // Act
        val result = repository.getTodoListFromLocal()

        // Assert
        assert(result is Resource.Error)
        assertEquals("There is no data", (result as Resource.Error).message)
    }

    @Test
    fun `getTodoListFromLocal should return error on GeneralException`() = runTest {
        // Arrange
        val errorMessage = "General error occurred"
        whenever(mockTodoDao.getAll()).thenThrow(GeneralException(errorMessage))

        // Act
        val result = repository.getTodoListFromLocal()

        // Assert
        assert(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).message)
    }
}