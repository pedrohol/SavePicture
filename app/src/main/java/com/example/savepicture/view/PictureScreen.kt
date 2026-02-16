package com.example.savepicture.view

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.savepicture.R
import com.example.savepicture.view.components.FloatingButton
import com.example.savepicture.view.components.PicturesGrid
import com.example.savepicture.viewModel.CameraViewModel
import com.example.savepicture.viewModel.PicturesViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlin.math.log


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PictureScreen(
    uri: ActivityResultLauncher<Uri>,
    cameraViewModel: CameraViewModel,
    pictureViewModel: PicturesViewModel) {

    val photoUri by cameraViewModel.photoUri.collectAsStateWithLifecycle()
    val pictureList by pictureViewModel.pictures.collectAsStateWithLifecycle()

    val permissionsToRequest = mutableListOf(Manifest.permission.CAMERA).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    val multiplePermissionsState = rememberMultiplePermissionsState(permissionsToRequest)

    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            photoUri?.let { photo ->
                photo.let {
                    uri.launch(it)
                }
            }
        } else {
            Log.e("Camera", "Foto não capturada.")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Log.i("LISTA", "$pictureList")

        if(pictureList.isNotEmpty()) {
            PicturesGrid(pictureList)
        }

    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.BottomEnd) {

        FloatingButton() {

            if(multiplePermissionsState.allPermissionsGranted) {
                val photo = cameraViewModel.preparePhotoUri()
                photo.let { takePictureLauncher.launch(it) }

            } else {
                multiplePermissionsState.launchMultiplePermissionRequest()
            }
        }
    }
}


