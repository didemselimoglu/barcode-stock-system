package com.didem.barcodeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.didem.barcodeapp.R
import com.didem.barcodeapp.viewmodel.ProductViewModel
import com.didem.barcodeapp.viewmodel.ProductState




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarcodeScreen(
    productViewModel: ProductViewModel,
    onLogout: () -> Unit, //çıkış yapma
    onCameraScan: () -> Unit, //tarama ekranına geçiş
    onProductFound: (String) -> Unit
) {
    var barcodeInput by remember { mutableStateOf("") }
    val productState by productViewModel.productState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        //arka plan resmi
        Image(
            painter = painterResource(id = R.drawable.b), //resim res/drawble paketine kpyalandı
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // üst kısım barkod okuyucu yazısı ve çıkış düğmesi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Barkod Okuyucu",
                    style = MaterialTheme.typography.headlineMedium
                )
                Button(
                    onClick = { onLogout() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Çıkış"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Çıkış")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Manuel Barkod Girişi
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Manuel Giriş",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Manuel Barkod Girişi",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = barcodeInput,
                        onValueChange = { barcodeInput = it },
                        label = { Text("Barkod kodunuzu yazınız") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                if (barcodeInput.isNotEmpty()){
                                    productViewModel.getProductByBarcode(barcodeInput)
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (barcodeInput.isNotEmpty()) {
                                productViewModel.getProductByBarcode(barcodeInput)
                            }
                        },
                        enabled = productState !is ProductState.Loading && barcodeInput.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        if (productState is ProductState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White
                            )
                        } else {
                            Text("Ürün Ara")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "veya",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(5.dp))

            // 2. Kamera ile Barkod Tarama
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Kamera ile Tarama",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onCameraScan() }, // Bu artık CameraScanScreen'e navigasyon yapacak
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Barkod'u Tarayınız")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sonuç gösterimi
            when (val state = productState) {
                is ProductState.Success -> {
                    LaunchedEffect(state.product) {
                        if (barcodeInput.isNotEmpty()) {
                            productViewModel.resetState()
                            onProductFound(barcodeInput)
                            barcodeInput = ""
                        }
                    }
                }

                is ProductState.Error -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = state.message,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }

                is ProductState.Loading -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Ürün aranıyor...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}