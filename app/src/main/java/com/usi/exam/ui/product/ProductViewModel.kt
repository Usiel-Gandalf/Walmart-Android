package com.usi.exam.ui.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usi.exam.di.interceptor.SimpleTokenProvider
import com.usi.exam.domain.models.Movie
import com.usi.exam.domain.models.Product
import com.usi.exam.domain.repository.ProductRepository
import com.usi.exam.domain.request.RegisterProductRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productKey = mutableStateOf("")
    val productKey: State<String> get() = _productKey

    private val _nameProduct = mutableStateOf("")
    val nameProduct: State<String> get() = _nameProduct

    private val _description = mutableStateOf("")
    val description: State<String> get() = _description

    private val _price = mutableStateOf("")
    val price: State<String> get() = _price

    private val _urlImage = mutableStateOf("")
    val urlImage: State<String> get() = _urlImage

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _search = mutableStateOf("")
    val search: State<String> get() = _search


    init {
        getProductList()
    }

    fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = productRepository.getAllProducts()
            if (result.estatus) {
                _productList.postValue(result.allProducts)
            } else {
                println("Algo salio mal mi ninio")
            }
        }
    }

    fun deleteProductById(idProduct: Int){
        viewModelScope.launch {
            val response = productRepository.deleteProductById(id = idProduct)
            if(response.estatus){
                println("Producto eliminado correctamente")
            }else{
                println("Algo salio mal eliminando el producto")
            }
        }
    }

    fun registerProduct(
        productKey: String, nameProduct: String, description: String,
        price: String, urlImage: String,
    ) {
        viewModelScope.launch {
            val product = RegisterProductRequest(
                productKey = productKey,
                name = nameProduct,
                description = description,
                price = price.toDouble(),
                imageUrl = urlImage
            )

            val response = productRepository.createProduct(product = product)
            if(response.estatus){
                println("Producto registrado correctamente")
            }else{
                println("Algo salio mal con el registro")
            }
        }

    }

    fun updateSearchText(text: CharSequence?) {
        _search.value = text.toString()
    }

    fun updateProductKeyText(text: CharSequence?) {
        _productKey.value = text.toString()
    }

    fun updateNameProductText(text: CharSequence?) {
        _nameProduct.value = text.toString()
    }

    fun updateDescriptionText(text: CharSequence?) {
        _description.value = text.toString()
    }

    fun updatePriceText(text: CharSequence?) {
        _price.value = text.toString()
    }

    fun updateUrlImageText(text: CharSequence?) {
        _urlImage.value = text.toString()
    }
}