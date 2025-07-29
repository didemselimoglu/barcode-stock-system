
package com.didem.barcodeapp.ui.screen

import android.Manifest
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.didem.barcodeapp.network.BarcodeScanner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CameraScanScreen(
    onBarcodeDetected: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }

    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Üst bar
        TopAppBar(
            title = { Text("Barkod Tarama") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Geri"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        if (cameraPermissionState.status.isGranted) {
            CameraPreview(
                onBarcodeDetected = onBarcodeDetected,
                cameraExecutor = cameraExecutor,
                lifecycleOwner = lifecycleOwner,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Barkod tarayabilmek için kamera iznine ihtiyacımız var",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() }
                ) {
                    Text("Kamera İzni Ver")
                }
            }
        }
    }
}

@Composable
private fun CameraPreview(
    onBarcodeDetected: (String) -> Unit,
    cameraExecutor: ExecutorService,
    lifecycleOwner: androidx.lifecycle.LifecycleOwner,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var hasDetected by remember { mutableStateOf(false) }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, BarcodeScanner { barcode ->
                            if (!hasDetected) {
                                hasDetected = true
                                onBarcodeDetected(barcode)
                            }
                        })
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )
                } catch (exc: Exception) {
                    // Hata yönetimi
                }

            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
        modifier = modifier
    )
}