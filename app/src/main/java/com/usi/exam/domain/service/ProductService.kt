package com.usi.exam.domain.service

import com.usi.exam.domain.request.RegisterProductRequest
import com.usi.exam.domain.request.UpdateProductRequest
import com.usi.exam.utils.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductService {

    @GET("getallproducts")
    suspend fun getAllProducts(): Response<ProductResponse>

    @GET("getproductbyid/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductResponse>

    @POST("createproduct")
    suspend fun createProduct(@Body product: RegisterProductRequest): Response<ProductResponse>

    @PUT("updateproduct")
    suspend fun updateProduct(@Body product: UpdateProductRequest): Response<ProductResponse>

    @DELETE("deleteproductbyid/{id}")
    suspend fun deleteProductById(@Path("id") id: Int): Response<ProductResponse>
}