package com.example.savepicture.di


import com.example.savepicture.viewModel.CameraViewModel
import com.example.savepicture.viewModel.PicturesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModule = module {

    viewModel{
        PicturesViewModel(get())
    }

    viewModel{
        CameraViewModel(get())
    }

}