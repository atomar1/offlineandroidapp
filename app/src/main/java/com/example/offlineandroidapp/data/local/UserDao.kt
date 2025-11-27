package com.example.offlineandroidapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchUsers(query: String): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}