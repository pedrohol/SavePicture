package com.example.savepicture.di


import androidx.room.Room
import com.example.savepicture.database.PictureDao
import com.example.savepicture.database.PictureDatabase
import com.example.savepicture.repository.PictureRepository
import com.example.savepicture.view.PictureScreen
import com.example.savepicture.viewModel.CameraViewModel
import com.example.savepicture.viewModel.PicturesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelModule = module {

    viewModel{
        PicturesViewModel(get(), get())
    }

    viewModel{
        CameraViewModel(get())
    }

}

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            PictureDatabase::class.java,
            "pictures_database"
        ).build()
    }

    single { get<PictureDatabase>().pictureDao() }

    factory { PictureRepository(get()) }

}

val listModules = listOf(
    viewModelModule,
    databaseModule
)

