package com.usi.exam.domain.request

data class RegisterRequest (
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val country: String
        )