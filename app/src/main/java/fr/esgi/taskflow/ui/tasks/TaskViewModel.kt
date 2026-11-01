package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.taskflow.domain.model.Task
import fr.esgi.taskflow.domain.usecase.AddTaskUseCase
import fr.esgi.taskflow.domain.usecase.ObserveTasksUseCase
import fr.esgi.taskflow.domain.usecase.SetCompletedUseCase
import fr.esgi.taskflow.domain.usecase.SetImportantUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update

data class TaskUiState(val tasks: List<Task> = emptyList())

class TaskViewModel(
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val setCompletedUseCase: SetCompletedUseCase,
    private val setImportantUseCase: SetImportantUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState(tasks = observeTasksUseCase().value))
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeTasksUseCase().collect { tasks -> _uiState.update { it.copy(tasks = tasks) } }
        }
    }

    fun addTask(title: String, description: String, important: Boolean) {
        addTaskUseCase(title, description, important)
    }

    fun setCompleted(taskId: Int, completed: Boolean) {
        setCompletedUseCase(taskId, completed)
    }

    fun setImportant(taskId: Int, important: Boolean) {
        setImportantUseCase(taskId, important)
    }
}
