package fr.esgi.taskflow.domain.usecase

import fr.esgi.taskflow.data.repository.TaskRepository
import fr.esgi.taskflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskUseCasesTest {
    private class FakeRepository : TaskRepository {
        override val tasks: StateFlow<List<Task>> = MutableStateFlow(emptyList())
        var added: Triple<String, String, Boolean>? = null

        override fun addTask(title: String, description: String, important: Boolean) {
            added = Triple(title, description, important)
        }

        override fun setCompleted(taskId: Int, completed: Boolean) = Unit
        override fun setImportant(taskId: Int, important: Boolean) = Unit
    }

    @Test
    fun addTaskTrimsInputAndRejectsBlankTitle() {
        val repository = FakeRepository()
        val addTask = AddTaskUseCase(repository)

        addTask("  Cours  ", "  Kotlin  ", true)
        assertEquals(Triple("Cours", "Kotlin", true), repository.added)

        repository.added = null
        addTask("   ", "Ignoré", false)
        assertEquals(null, repository.added)
    }

    @Test
    fun observeTasksReturnsRepositoryFlow() {
        val repository = FakeRepository()
        assertEquals(repository.tasks, ObserveTasksUseCase(repository)())
    }
}
