package fr.esgi.taskflow.data.network

import fr.esgi.taskflow.domain.model.Task

// JSONPlaceholder /todos fournit userId, id, title et completed.
// Les champs sont nullable pour pouvoir ignorer une réponse incomplète.
data class TaskDto(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val completed: Boolean?,
)

fun TaskDto.toDomainOrNull(): Task? {
    val validId = id?.takeIf { it > 0 } ?: return null
    val validTitle = title?.trim()?.takeIf { it.isNotEmpty() } ?: return null
    return Task(
        id = validId,
        title = validTitle,
        description = userId?.let { "Utilisateur $it · JSONPlaceholder" } ?: "JSONPlaceholder",
        completed = completed ?: false,
    )
}
