package com.example.savepicture.view

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savepicture.R
import com.example.savepicture.view.components.FloatingButton
import com.example.savepicture.view.components.PicturesGrid
import com.example.savepicture.viewModel.CameraViewModel
import com.example.savepicture.viewModel.PicturesViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlin.math.log


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PictureScreen(uri: ActivityResultLauncher<Uri>) {

    val cameraViewModel: CameraViewModel = viewModel()
    val photoUri by cameraViewModel.photoUri.collectAsStateWithLifecycle()

    val pictureViewModel: PicturesViewModel = viewModel()
    val pictureList by pictureViewModel.pictures.collectAsStateWithLifecycle()

    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    Box(modifier = Modifier.fillMaxSize()) {

        PicturesGrid(pictureList)

    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.BottomEnd) {

        FloatingButton() {

            if(cameraPermission.status.isGranted) {
                cameraViewModel.preparePhotoUri()

                photoUri.let { photo ->
                    photo?.let {
                        uri.launch(it)
                    }
                }

            } else {
                cameraPermission.launchPermissionRequest()
            }

        }
    }

}


