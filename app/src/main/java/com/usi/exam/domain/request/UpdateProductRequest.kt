package com.usi.exam.domain.request

data class UpdateProductRequest (
    val id: Int,
    val productKey: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
        )