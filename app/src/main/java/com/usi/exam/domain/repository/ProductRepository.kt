package com.usi.exam.domain.repository

import com.usi.exam.domain.request.RegisterProductRequest
import com.usi.exam.domain.request.UpdateProductRequest
import com.usi.exam.domain.service.ProductService
import com.usi.exam.utils.ProductResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productService: ProductService){
    suspend fun getAllProducts(): ProductResponse{
        val response = productService.getAllProducts()
        return response.body() ?: ProductResponse(message = response.message())
    }

    suspend fun getProductById(id: Int): ProductResponse{
        val response = productService.getProductById(id = id)
        return response.body() ?: ProductResponse(message = response.message())
    }

    suspend fun createProduct(product: RegisterProductRequest): ProductResponse{
        val response = productService.createProduct(product = product)
        return response.body() ?: ProductResponse(message = response.message())
    }

    suspend fun updateProduct(product: UpdateProductRequest): ProductResponse{
        val response = productService.updateProduct(product = product)
        return response.body() ?: ProductResponse(message = response.message())
    }

    suspend fun deleteProductById(id: Int): ProductResponse{
        val response = productService.deleteProductById(id = id)
        return response.body() ?: ProductResponse(message = response.message())
    }
}