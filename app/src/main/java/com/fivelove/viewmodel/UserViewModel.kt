package com.fivelove.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fivelove.db.model.User
import com.fivelove.db.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Nguyen Kim Khanh on 6/30/2020.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {
    val currentUser: LiveData<User>
    private var repository: UserRepository = UserRepository.getInstance()

    fun updateUser(user: User?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: User?){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
    init {
        currentUser = repository.user
    }
}