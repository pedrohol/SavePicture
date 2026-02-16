package com.example.savepicture.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.savepicture.database.PictureDao
import com.example.savepicture.model.Pictures
import com.example.savepicture.repository.PictureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class PicturesViewModel(
    application: Application,
    private val repository: PictureRepository): AndroidViewModel(application) {

    val pictures: StateFlow<List<Pictures>>

    init {

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