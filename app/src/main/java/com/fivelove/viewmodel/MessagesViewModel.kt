package com.fivelove.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fivelove.App
import com.fivelove.db.model.Message
import com.fivelove.db.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
class MessagesViewModel(application: App) : AndroidViewModel(application) {
    private val repository: MessagesRepository = MessagesRepository(application)
    private val allMessages: LiveData<List<Message>>
    fun insertMessage(message: Message?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMessage(message)
        }
    }

    fun updateMessage(message: Message?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMessage(message)
        }
    }

    fun deleteMessage(message: Message?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMessage(message)
        }
    }

    init {
        allMessages = repository.allMessages
    }
}