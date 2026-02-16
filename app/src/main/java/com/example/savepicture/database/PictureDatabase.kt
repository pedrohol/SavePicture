package com.example.savepicture.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.savepicture.model.Pictures

@Database(entities = [Pictures::class], version = 1, exportSchema = false)
abstract class PictureDatabase: RoomDatabase() {
    abstract fun pictureDao(): PictureDao

}