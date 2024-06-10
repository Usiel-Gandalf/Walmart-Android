package com.usi.exam.ui.product

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usi.exam.R
import com.usi.exam.ui.components.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductView(
    productViewModel: ProductViewModel,
    logout: () -> Unit,
    navigateToBackView: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(navToBackView = { navigateToBackView() },
            title = "CREATE PRODUCT",
            logout = { logout() })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(all = 8.dp)
        ) {
            bodyCreateProduct(
                productKey = productViewModel.productKey.value,
                updateProductKeyText = {
                    productViewModel.updateProductKeyText(it)
                },
                nameProduct = productViewModel.nameProduct.value,
                updateNamePoroductText = {
                    productViewModel.updateNameProductText(it)
                },
                description = productViewModel.description.value,
                updateDescriptionText = {
                    productViewModel.updateDescriptionText(it)
                },
                price = productViewModel.price.value,
                updatePriceText = { productViewModel.updatePriceText(it) },
                urlImage = productViewModel.urlImage.value,
                updateUrlImageText = { productViewModel.updateUrlImageText(it) },
                onClicTryRegisterProduct = {
                    productViewModel.registerProduct(
                        productKey = productViewModel.productKey.value,
                        nameProduct = productViewModel.nameProduct.value,
                        description = productViewModel.description.value,
                        price = productViewModel.price.value,
                        urlImage = productViewModel.urlImage.value
                    )
                    navigateToBackView()
                }
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bodyCreateProduct(
    productKey: String,
    updateProductKeyText: (productKey: String) -> Unit,
    nameProduct: String,
    updateNamePoroductText: (nameProduct: String) -> Unit,
    description: String,
    updateDescriptionText: (description: String) -> Unit,
    price: String,
    updatePriceText: (price: String) -> Unit,
    urlImage: String,
    updateUrlImageText: (urlImage: String) -> Unit,
    onClicTryRegisterProduct: () -> Unit,
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

            // Clave del producto
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp, top = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = productKey,
                    onValueChange = { updateProductKeyText(it) },
                    label = { Text("Clave del producto") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //nameProduct
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(value = nameProduct, onValueChange = {
                    updateNamePoroductText(it)
                }, label = { Text("Nombre de producto") }, modifier = Modifier.fillMaxWidth()
                )
            }

            //description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = description,
                    onValueChange = {
                        updateDescriptionText(it)
                    },
                    label = { Text("Descripcion del producto") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //precio del produto
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(value = price, onValueChange = {
                    updatePriceText(it)
                }, label = { Text("Precio del producto") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
                    value = urlImage,
                    onValueChange = {
                        updateUrlImageText(it)
                    },
                    label = { Text("Url de la imagen del producto") },
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
                        onClicTryRegisterProduct.invoke()
                    }, modifier = Modifier.size(width = 300.dp, height = 60.dp)
                ) {
                    Text(text = "Registrar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}