package com.example.usersdb_room_codelab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.usersdb_room_codelab.data.UserDatabase
import com.example.usersdb_room_codelab.model.User
import com.example.usersdb_room_codelab.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allData = repository.allData
        }

    fun addUser(newUser: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(newUser)
        }
    }

    fun updateUser(updatedUser: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(updatedUser)
        }
    }

    fun deleteUser(deletedUser: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(deletedUser)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

}