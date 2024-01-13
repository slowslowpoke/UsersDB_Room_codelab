package com.example.usersdb_room_codelab.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.usersdb_room_codelab.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(newUser: User)

    @Update
    suspend fun updateUser(updatedUser: User)
    @Delete
    suspend fun deleteUser(deletedUser: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
    @Query("SELECT * FROM user_table ORDER BY firstName ASC")
    fun readAllData(): LiveData<List<User>>
}