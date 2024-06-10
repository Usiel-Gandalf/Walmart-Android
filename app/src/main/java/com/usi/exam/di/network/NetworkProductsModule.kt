package com.usi.exam.di.network

import com.usi.exam.di.interceptor.AuthInterceptor
import com.usi.exam.di.interceptor.SimpleTokenProvider
import com.usi.exam.di.interceptor.TokenProvider
import com.usi.exam.utils.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkProductsModule {

    @Provides
    @Singleton
    fun provideSimpleTokenProvider(): SimpleTokenProvider {
        return SimpleTokenProvider()
    }

    @Provides
    @Singleton
    fun provideTokenProvider(simpleTokenProvider: SimpleTokenProvider): TokenProvider {
        return simpleTokenProvider
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: TokenProvider): AuthInterceptor {
        return AuthInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    @Named("ProductInterceptor")
    fun providesOkHttpClientMovies(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    @Named("productRetrofit")
    fun provideMovieRetrofit(@Named("ProductInterceptor") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_PRODUCT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}