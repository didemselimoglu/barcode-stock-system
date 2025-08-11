package com.didem.barcodeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.didem.barcodeapp.R
import com.didem.barcodeapp.viewmodel.LoginState
import com.didem.barcodeapp.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel
) {

    val loginState by loginViewModel.loginState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // arka plan resmi
        Image(
            painter = painterResource(id = R.drawable.a), // resim drawable klasöründe
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
            Text(
                text = "Hoşgeldiniz",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                        value = loginViewModel.username, //ViewModelden geliyor
                        onValueChange = loginViewModel :: updateUsername,
                        label = { Text("Kullanıcı Adı") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Red,
                            focusedLabelColor = Color.Red
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = loginViewModel.password, //ViewModelden geliyor
                        onValueChange = loginViewModel :: updatePassword,
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
                            loginViewModel.login()
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

                        }
                    }
                }
            }
        }
    }
}
