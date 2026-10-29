package fr.esgi.taskflow.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskApiClient {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): TaskApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TaskApiService::class.java)
}
