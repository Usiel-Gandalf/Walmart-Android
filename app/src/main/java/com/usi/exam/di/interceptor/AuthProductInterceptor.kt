package com.usi.exam.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
            .build()
        return chain.proceed(newRequest)
    }
}

interface TokenProvider {
    fun getToken(): String
}

class SimpleTokenProvider : TokenProvider {
    private var token: String = ""

    override fun getToken(): String {
        return token
    }

    fun setToken(newToken: String) {
        token = newToken
    }
}