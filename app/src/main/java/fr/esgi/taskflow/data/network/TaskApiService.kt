package fr.esgi.taskflow.data.network

import retrofit2.http.GET

interface TaskApiService {
    @GET("todos")
    suspend fun getTodos(): List<TaskDto>
}
