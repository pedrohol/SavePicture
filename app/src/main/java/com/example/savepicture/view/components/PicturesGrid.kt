package com.example.savepicture.view.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.LocalRetainedValuesStoreProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import com.example.savepicture.model.Pictures
import java.io.File
import kotlin.math.log

@Composable
fun PicturesGrid(pictures: List<Pictures>) {

    val context = LocalContext.current

    LazyVerticalGrid(columns = GridCells.Adaptive(124.dp)) {

        items(items = pictures, key = {it.id}) {

            val uri = it.uri
            Log.i("LISTA", uri)

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(uri.toUri())
                    .crossfade(true)
                    .listener(
                        onError = { _, result ->
                            Log.e("CoilError", "Erro ao carregar: ${result.throwable}")
                        }
                    )
                    .build(),
                contentDescription = "Picture",
                placeholder = ColorPainter(Color.LightGray),
                error = ColorPainter(Color.Red),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            )
        }
    }
}