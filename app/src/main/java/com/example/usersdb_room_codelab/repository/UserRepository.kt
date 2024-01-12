package com.example.usersdb_room_codelab.repository

import androidx.lifecycle.LiveData
import com.example.usersdb_room_codelab.data.UserDao
import com.example.usersdb_room_codelab.model.User

class UserRepository(private val userDao: UserDao) {
    val allData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(newUser: User){
        userDao.addUser(newUser)
    }

    suspend fun updateUser(updatedUser: User){
        userDao.updateUser(updatedUser)
    }
}