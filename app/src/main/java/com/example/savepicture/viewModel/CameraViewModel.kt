package com.example.savepicture.viewModel

import android.R.attr.bitmap
import android.app.Application
import android.graphics.Bitmap
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
import java.io.FileOutputStream
import java.io.IOException

class CameraViewModel(application: Application): AndroidViewModel(application) {

    private val _photoUri = MutableStateFlow<Uri?>(null)
    val photoUri = _photoUri.asStateFlow()

    private var currentFile: File? = null

    fun preparePhotoUri(): Uri {
        val context = getApplication<Application>().applicationContext

        val storage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        if (storage?.exists() == false) {
            storage.mkdirs()
        }

        val file = File(storage, "IMG_${System.currentTimeMillis()}.jpg")
        currentFile = file

        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        _photoUri.value = uri
        return uri

    }
}