package com.usi.exam.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.usi.exam.R
import com.usi.exam.domain.models.Movie
import com.usi.exam.domain.models.Product
import com.usi.exam.ui.components.FloatingButton
import com.usi.exam.ui.components.SearchProduct
import com.usi.exam.ui.components.TopAppBarWithoutIconAndWithAction
import com.usi.exam.ui.main.LazyCardsForMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductView(productViewModel: ProductViewModel, logout: () -> Unit, onClickGotToCreateProductView: () -> Unit) {

    LaunchedEffect(true) {
        productViewModel.getProductList()
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val productList by productViewModel.productList.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBarWithoutIconAndWithAction(
                    title = "PRODUCT",
                    logout = {
                        logout()
                    },
                    scrollBehavior = scrollBehavior
                )

                SearchProduct(searchValue = productViewModel.search.value,
                    updateSearchText = {
                        productViewModel.updateSearchText(it)
                    }) {

                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(all = 8.dp)
                ) {
                    productList?.let { it1 -> LazyCardsForProducts(productList = it1, deleteProduct = {
                        productViewModel.deleteProductById(it)
                    }) }
                }
            }
        )
        FloatingActionButton(
            onClick = {
                      onClickGotToCreateProductView()
                 },
            containerColor = Color.Blue,
            contentColor = Color.Blue,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp) // Ajusta este valor según el tamaño del BottomBar
        ) {
            Icon(Icons.Rounded.Add, contentDescription = "Add", tint = Color.White)
        }

    }
}

@Composable
fun LazyCardsForProducts(
    productList: List<Product>,
    deleteProduct: (idProduct: Int) -> Unit
) {
    if (productList.isNotEmpty()) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(productList.size) {
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(360.dp)
                        .fillMaxSize()
                        .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                ) {
                    Box {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            painter = rememberAsyncImagePainter(productList[it].imageUrl),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(5.dp)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .padding(1.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            deleteProduct(productList[it].id)
                                        }
                                )

                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.White
                                )

                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Text(
                        text = productList[it].name,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(30.dp)
                            .align(Alignment.CenterHorizontally)
                            .verticalScroll(rememberScrollState(0))
                            .background(Color.Black)
                    )
                }
            }
        }
    } else {
        Text(
            text = "Sin resultado", color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)

        )
    }
}