package com.usi.exam.ui.security.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usi.exam.R
import com.usi.exam.ui.components.TopAppBarWithBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    registerViewModel: RegisterViewModel,
    onClicGoToLogin: () -> Unit,
    onLoginNavigateToMain: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBarWithBackIcon(navToBackView = {
            onClicGoToLogin()
        }, title = "REGISTER")
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(all = 8.dp)
        ) {
            RegisterBody(
                email = registerViewModel.email.value,
                updateEmailText = {
                    registerViewModel.updateEmailText(it)
                },
                password = registerViewModel.password.value,
                updatePasswordText = {
                    registerViewModel.updatePasswordText(it)
                },
                firstname = registerViewModel.firstname.value,
                updateFirstnameText = {
                    registerViewModel.updateFirstameText(it)
                },
                lastname = registerViewModel.lastname.value,
                updateLastnameText = {
                    registerViewModel.updateLastnameText(it)
                },
                country = registerViewModel.country.value,
                updateCountryText = {
                    registerViewModel.updateCountryText(it)
                },
                onClicTryRegisterUser = {
                    registerViewModel.registerUser(
                        registerViewModel.email.value,
                        registerViewModel.password.value,
                        registerViewModel.firstname.value,
                        registerViewModel.lastname.value,
                        registerViewModel.country.value,
                    )
                    if (registerViewModel.registerStatusFinal.value) {
                        onLoginNavigateToMain()
                    } else {
                        Toast.makeText(
                            context,
                            "Algo salio mal, intente de nuevo.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBody(
    email: String,
    updateEmailText: (email: String) -> Unit,
    password: String,
    updatePasswordText: (password: String) -> Unit,
    firstname: String,
    updateFirstnameText: (firstname: String) -> Unit,
    lastname: String,
    updateLastnameText: (lastname: String) -> Unit,
    country: String,
    updateCountryText: (country: String) -> Unit,
    onClicTryRegisterUser: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.register_avatar), // Reemplaza con tu recurso SVG
                    contentDescription = "Avatar", // Reemplaza con tu string resource
                    modifier = Modifier.size(200.dp) // Ajusta el tamaño según sea necesario
                )
            }

            // EMAIL
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp, top = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = email,
                    onValueChange = { updateEmailText(it) },
                    label = { Text("Email/Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //PASSWORD
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = password,
                    onValueChange = {
                        updatePasswordText(it)
                    },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //firstname
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = firstname,
                    onValueChange = {
                        updateFirstnameText(it)
                    },
                    label = { Text("Firstname") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //lastname
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = lastname,
                    onValueChange = {
                        updateLastnameText(it)
                    },
                    label = { Text("lastname") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //country
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = country,
                    onValueChange = {
                        updateCountryText(it)
                    },
                    label = { Text("Country") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //Boton
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        onClicTryRegisterUser.invoke()
                    },
                    modifier = Modifier
                        .size(width = 300.dp, height = 60.dp)
                ) {
                    Text(text = "Registrarme", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}