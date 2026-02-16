package com.example.savepicture.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.savepicture.model.Pictures
import com.example.savepicture.ui.theme.SavePictureTheme
import com.example.savepicture.view.PictureScreen
import com.example.savepicture.viewModel.CameraViewModel
import com.example.savepicture.viewModel.PicturesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val cameraViewModel: CameraViewModel by viewModel()
    private val pictureViewModel: PicturesViewModel by viewModel()

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {

            val picture = Pictures(id = 0, uri = cameraViewModel.photoUri.value.toString())
            pictureViewModel.addPicture(picture)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            SavePictureTheme {
                PictureScreen(
                    uri = takePictureLauncher,
                    cameraViewModel = cameraViewModel,
                    pictureViewModel = pictureViewModel)
            }
        }
    }

}