package com.example.offlineandroidapp.data.remote

import com.example.offlineandroidapp.data.local.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}