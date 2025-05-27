package com.example.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>
}

data class User(
    val id: Int = 0,
    val name: String = "",
    val email: String = ""
)