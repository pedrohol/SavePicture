package com.example.savepicture.viewModel

import android.app.Application
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class CameraViewModel(application: Application): AndroidViewModel(application) {

    private val _photoUri = MutableStateFlow<Uri?>(null)
    val photoUri = _photoUri.asStateFlow()

    private var currentFile: File? = null

    fun preparePhotoUri() {
        val context = getApplication<Application>().applicationContext

        val storage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", storage)
        currentFile = file

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        _photoUri.value = uri

    }


}