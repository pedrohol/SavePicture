package com.example.savepicture.view.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.savepicture.R

@Composable
fun FloatingButton(onClick: () -> Unit ) {

    FloatingActionButton(onClick = onClick) {
        Icon(painter = painterResource(R.drawable.camera_icon),
            contentDescription = "Camera Icon")
    }

}
