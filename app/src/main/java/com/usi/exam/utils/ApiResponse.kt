package com.usi.exam.utils

data class ApiResponse(
    val estatus: Boolean = false,
    val message: String = "",
    val token: String? = null
)