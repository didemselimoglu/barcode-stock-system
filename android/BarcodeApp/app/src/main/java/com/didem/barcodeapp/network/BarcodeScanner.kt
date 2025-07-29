package com.didem.barcodeapp.network

// CameraX ile barkod görseli alma
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy

//ML Kit'in barkod okuma kütüphanesi ile
// BarcodeScanning API'sı ile barkod analizi
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeScanner(

    //barkod analizi ve gelen barkodu bildirme
    private val onBarcodeDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(image)//barkod cözümleme
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        barcode.rawValue?.let { value ->
                            onBarcodeDetected(value)
                        }
                    }
                }
                .addOnFailureListener {
                    // Hata yönetimi
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}