package com.didem.barcodeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.didem.barcodeapp.data.repository.AuthRepository
import com.didem.barcodeapp.data.repository.ProductRepository
import com.didem.barcodeapp.ui.screen.LoginScreen
import com.didem.barcodeapp.ui.screen.BarcodeScreen
import com.didem.barcodeapp.ui.screen.CameraScanScreen
import com.didem.barcodeapp.ui.screen.ProductDetailScreen
import com.didem.barcodeapp.ui.theme.BarcodeAppTheme
import com.didem.barcodeapp.viewmodel.LoginViewModel
import com.didem.barcodeapp.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {                                                                                                                                                                    
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authRepository = AuthRepository(this)
        val productRepository = ProductRepository()

        val productViewModel = ProductViewModel(productRepository, authRepository)

        setContent {
            BarcodeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    //token kontrolü
                    val startDestination = if (authRepository.getToken() != null) "barcode" else "login"

                    NavHost(navController, startDestination) {
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("barcode") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                loginViewModel = LoginViewModel(authRepository)
                            )
                        }

                        //barkod ekranı
                        composable("barcode") {
                            BarcodeScreen(
                                productViewModel = productViewModel,

                                //çıkış için token silme
                                onLogout = {
                                    authRepository.clearToken()
                                    navController.navigate("login") {
                                        popUpTo("barcode") { inclusive = true }
                                    }
                                },

                                //kamera ekranı
                                onCameraScan = {
                                    navController.navigate("camera_scan")
                                },

                                //ürün detay ekranı
                                onProductFound = { barcodeValue ->
                                    if (barcodeValue.isNotEmpty() && barcodeValue.isNotBlank()) {
                                        productViewModel.resetState()
                                        navController.navigate("product_detail/$barcodeValue")
                                    }
                                }
                            )
                        }

                        composable("camera_scan") {
                            CameraScanScreen(
                                onBarcodeDetected = { barcode ->
                                    // Barkod okundu, ürün çağrısı yap ve detay ekranına git
                                    productViewModel.resetState()
                                    productViewModel.getProductByBarcode(barcode)
                                    navController.navigate("product_detail/$barcode") {
                                        popUpTo("camera_scan") { inclusive = true }
                                    }
                                },
                                onBackPressed = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = "product_detail/{barcodeValue}",
                            arguments = listOf(navArgument("barcodeValue") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val barcodeValue = backStackEntry.arguments?.getString("barcodeValue") ?: ""
                            ProductDetailScreen(
                                productViewModel = productViewModel,
                                barcodeValue = barcodeValue,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
