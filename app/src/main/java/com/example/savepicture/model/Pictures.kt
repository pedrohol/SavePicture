package com.example.savepicture.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures_table")
data class Pictures (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "uri")
    val uri: String
)