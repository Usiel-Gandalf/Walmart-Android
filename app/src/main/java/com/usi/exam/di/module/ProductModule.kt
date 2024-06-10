package com.usi.exam.di.module

import com.usi.exam.domain.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {

    @Provides
    @Singleton
    fun provideProductService(@Named("productRetrofit") retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)
}