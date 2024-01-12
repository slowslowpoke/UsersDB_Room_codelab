package com.example.usersdb_room_codelab.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}