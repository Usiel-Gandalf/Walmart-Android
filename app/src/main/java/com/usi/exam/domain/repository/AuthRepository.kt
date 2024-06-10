package com.usi.exam.domain.repository

import com.usi.exam.domain.request.LoginRequest
import com.usi.exam.domain.request.RegisterRequest
import com.usi.exam.domain.service.AuthService
import com.usi.exam.utils.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val apiService: AuthService){

    suspend fun register(user: RegisterRequest): ApiResponse {
        val response = apiService.register(user)
        return response.body() ?: ApiResponse(message = response.message())
    }

    suspend fun login(credentials: LoginRequest): ApiResponse {
        val response = apiService.login(credentials)
        return response.body() ?: ApiResponse( message = response.message())
    }
}