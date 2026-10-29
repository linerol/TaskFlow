package fr.esgi.taskflow.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.esgi.taskflow.data.repository.TaskRepository
import fr.esgi.taskflow.domain.usecase.AddTaskUseCase
import fr.esgi.taskflow.domain.usecase.ObserveTasksUseCase
import fr.esgi.taskflow.domain.usecase.SetCompletedUseCase
import fr.esgi.taskflow.domain.usecase.SetImportantUseCase

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(TaskViewModel::class.java))
        return TaskViewModel(
            observeTasksUseCase = ObserveTasksUseCase(repository),
            addTaskUseCase = AddTaskUseCase(repository),
            setCompletedUseCase = SetCompletedUseCase(repository),
            setImportantUseCase = SetImportantUseCase(repository),
        ) as T
    }
}
