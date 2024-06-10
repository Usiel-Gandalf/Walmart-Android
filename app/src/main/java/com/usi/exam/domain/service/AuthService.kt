package com.usi.exam.domain.service

import com.usi.exam.domain.models.User
import com.usi.exam.domain.request.LoginRequest
import com.usi.exam.domain.request.RegisterRequest
import com.usi.exam.utils.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("register")
    suspend fun register(@Body user: RegisterRequest): Response<ApiResponse>

    @POST("login")
    suspend fun login(@Body credentials: LoginRequest): Response<ApiResponse>
}