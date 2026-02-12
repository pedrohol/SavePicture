package com.example.savepicture.repository

import androidx.lifecycle.LiveData
import com.example.savepicture.database.PictureDao
import com.example.savepicture.model.Pictures

class PictureRepository(private val picturesDao: PictureDao) {

    fun readAllPictures() = picturesDao.getAllPictures()

    suspend fun addPicture(pictures: Pictures) {
        picturesDao.addPicture(pictures)
    }

    suspend fun deletePicture(pictures: Pictures) {
        picturesDao.deletePicture(pictures)
    }

}