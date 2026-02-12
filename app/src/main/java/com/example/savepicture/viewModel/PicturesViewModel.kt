package com.example.savepicture.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.savepicture.database.PictureDatabase
import com.example.savepicture.model.Pictures
import com.example.savepicture.repository.PictureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PicturesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PictureRepository

    val pictures: StateFlow<List<Pictures>>

    init {
        val picturesDao = PictureDatabase.getDatabase(application).pictureDao()
        repository = PictureRepository(picturesDao)

        pictures = repository.readAllPictures()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun addPicture(picture: Pictures) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPicture(picture)
        }
    }

}