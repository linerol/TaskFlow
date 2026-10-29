package fr.esgi.taskflow.data.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TaskDtoTest {
    @Test
    fun mapsJsonPlaceholderTodoToTask() {
        val task = TaskDto(userId = 3, id = 7, title = "  Lire  ", completed = true).toDomainOrNull()

        assertEquals(7, task?.id)
        assertEquals("Lire", task?.title)
        assertEquals(true, task?.completed)
        assertEquals(false, task?.important)
        assertEquals("Utilisateur 3 · JSONPlaceholder", task?.description)
    }

    @Test
    fun ignoresMissingOrInvalidRequiredFields() {
        assertNull(TaskDto(1, null, "Lire", false).toDomainOrNull())
        assertNull(TaskDto(1, 0, "Lire", false).toDomainOrNull())
        assertNull(TaskDto(1, 2, "   ", false).toDomainOrNull())
    }
}
