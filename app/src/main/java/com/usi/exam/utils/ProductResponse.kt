package com.usi.exam.utils

import com.usi.exam.domain.models.Product
import java.util.Optional

data class ProductResponse(
    val estatus: Boolean = false,
    val message: String = "",
    val allProducts: List<Product> = emptyList(),
    val product : Optional<Product> = Optional.empty()
)