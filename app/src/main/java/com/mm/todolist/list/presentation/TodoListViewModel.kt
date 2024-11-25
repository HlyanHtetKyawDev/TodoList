package com.mm.todolist.list.presentation

import androidx.lifecycle.viewModelScope
import com.mm.todolist.core.data.network.utils.Resource
import com.mm.todolist.core.presentation.BaseViewModel
import com.mm.todolist.list.domain.useCase.TodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoListUseCase: TodoListUseCase
) : BaseViewModel<TodoListEvent>() {

    private val _state = MutableStateFlow(TodoListUiState())
    val state = _state.onStart {
        getTodoList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), TodoListUiState())

    private var _loading: Boolean
        get() = state.value.isLoading
        set(value) = _state.update { it.copy(isLoading = value) }

    private fun getTodoList() {
        _state.update {
            it.copy(
                isLoading = false,
                todoList = emptyList()
            )
        }
        CoroutineScope(Dispatchers.IO).launch {
            todoListUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                        emitEvent(TodoListEvent.Error(result.error))
                    }

                    is Resource.Loading -> _loading = true
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                todoList = result.data.orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }

}
