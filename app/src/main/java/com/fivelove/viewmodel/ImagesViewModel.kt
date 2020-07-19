package com.fivelove.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fivelove.App
import com.fivelove.db.model.Image
import com.fivelove.db.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
class ImagesViewModel(application: App) : AndroidViewModel(application) {
    val allImages: LiveData<List<Image>>
    var idUser: String
        get() {
            TODO()
        }
        set(value) {}


    private val repository: ImageRepository = ImageRepository(application)

    fun insertImage(image: Image?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertImage(image)
        }
    }

    fun updateImage(image: Image?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateImage(image)
        }
    }

    fun deleteImage(image: Image?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteImage(image)
        }
    }

    init {
        repository.idFriend = idUser
        allImages = repository.allImages
    }


}