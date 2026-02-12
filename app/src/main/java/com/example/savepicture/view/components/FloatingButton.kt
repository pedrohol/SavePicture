package com.example.savepicture.view.components

import android.content.res.loader.ResourcesProvider
import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.savepicture.R

@Composable
fun FloatingButton(onClick: () -> Unit ) {

    FloatingActionButton(onClick = onClick) {
        Icon(painter = painterResource(R.drawable.camera_icon),
            contentDescription = "Camera Icon")
    }

}
