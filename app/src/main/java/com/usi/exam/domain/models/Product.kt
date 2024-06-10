package com.usi.exam.domain.models

data class Product(
    val id: Int,
    val productKey: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
