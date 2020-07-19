package com.fivelove.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fivelove.db.model.User
import com.fivelove.db.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
class UsersViewModel(application: Application) : AndroidViewModel(application) {
    val allFriends: LiveData<List<User>>
    private val repository: UsersRepository = UsersRepository.getInstance()
    fun insertUser(friend: User?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(friend)
        }
    }

    fun deleteUser(friend: User?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(friend)
        }
    }

    fun updateUser(friend: User?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(friend)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

    init {
        allFriends = repository.allFriends
    }
}