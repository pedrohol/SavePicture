package com.example.savepicture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savepicture.model.Pictures
import com.example.savepicture.ui.theme.SavePictureTheme
import com.example.savepicture.view.PictureScreen
import com.example.savepicture.view.components.PicturesGrid
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
        super.onCreate(savedInstanceState)
        setContent {
            SavePictureTheme {
                PictureScreen(takePictureLauncher, cameraViewModel)
            }
        }
    }

}