package com.didem.barcodeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.didem.barcodeapp.R
import com.didem.barcodeapp.viewmodel.LoginViewModel
import com.didem.barcodeapp.viewmodel.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by loginViewModel.loginState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Arka plan resmi
        Image(
            painter = painterResource(id = R.drawable.a), // Resim drawable klasöründe
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Login formu
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .padding(top = 120.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hoşgeldiniz metni - sadece yazı olarak
            Text(
                text = "Hoşgeldiniz",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp)) // Biraz boşluk bırakmak için

            // Input alanları için şeffaf arka plan
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Kullanıcı Adı") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Red,
                            focusedLabelColor = Color.Red
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Şifre") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Red,
                            focusedLabelColor = Color.Red
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Kırmızı buton
                    Button(
                        onClick = {
                            loginViewModel.login(username, password)
                        },
                        enabled = loginState !is LoginState.Loading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        if (loginState is LoginState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White
                            )
                        } else {
                            Text("Giriş Yap")
                        }
                    }

                    when (loginState) {
                        is LoginState.Error -> {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = (loginState as LoginState.Error).message,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        is LoginState.Success -> {
                            LaunchedEffect(loginState) {
                                onLoginSuccess()
                            }
                        }

                        else -> {
                            // Idle veya Loading durumunda ekstra bir şey göstermek istersen buraya
                        }
                    }
                }
            }
        }
    }
}
