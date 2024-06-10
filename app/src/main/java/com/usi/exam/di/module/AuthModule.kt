package com.usi.exam.di.module

import com.usi.exam.domain.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthService(@Named("localhostRetrofit") retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

}