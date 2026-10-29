package fr.esgi.taskflow.domain.usecase

import fr.esgi.taskflow.data.repository.TaskRepository
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.StateFlow

class ObserveTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(): StateFlow<List<Task>> = repository.tasks
}

class AddTaskUseCase(private val repository: TaskRepository) {
    operator fun invoke(title: String, description: String, important: Boolean) {
        val cleanTitle = title.trim()
        if (cleanTitle.isNotEmpty()) repository.addTask(cleanTitle, description.trim(), important)
    }
}

class SetCompletedUseCase(private val repository: TaskRepository) {
    operator fun invoke(taskId: Int, completed: Boolean) = repository.setCompleted(taskId, completed)
}

class SetImportantUseCase(private val repository: TaskRepository) {
    operator fun invoke(taskId: Int, important: Boolean) = repository.setImportant(taskId, important)
}
