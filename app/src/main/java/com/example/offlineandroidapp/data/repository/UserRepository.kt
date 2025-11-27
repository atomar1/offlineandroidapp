package com.example.offlineandroidapp.data.repository

import com.example.offlineandroidapp.data.local.User
import com.example.offlineandroidapp.data.local.UserDao
import com.example.offlineandroidapp.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userDao: UserDao,
    private val apiService: ApiService
) {

    // Observe users from database
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    // Search users locally
    fun searchUsers(query: String): Flow<List<User>> = userDao.searchUsers(query)

    // Fetch and cache users (Offline-First Pattern)
    fun refreshUsers(): Flow<Result<Unit>> = flow {
        try {
            // Fetch from API
            val users = apiService.getUsers()
            // Save to database
            userDao.insertUsers(users)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            // Emit failure but don't crash - cached data still available
            emit(Result.failure(e))
        }
    }
}