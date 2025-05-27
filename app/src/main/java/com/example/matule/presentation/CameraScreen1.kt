package com.example.matule.presentation

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun CameraScreen1() {

    val context = LocalContext.current

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var urlPath by remember { mutableStateOf<String?>(null) }

    val permission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    val gallery = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
    }

    Column {
        bitmap?.let {
            Image(
                it.asImageBitmap(),
                contentDescription = null
            )
        }

        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permission.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }else {
                    permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                gallery.launch("image/*")
            }
        ) { }
    }
}