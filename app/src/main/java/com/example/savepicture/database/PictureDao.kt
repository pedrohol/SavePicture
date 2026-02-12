package com.example.savepicture.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.savepicture.model.Pictures
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {

    @Query("SELECT * FROM pictures_table ")
    fun getAllPictures(): Flow<List<Pictures>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPicture(pictures: Pictures)

    @Delete
    suspend fun deletePicture(pictures: Pictures)

}