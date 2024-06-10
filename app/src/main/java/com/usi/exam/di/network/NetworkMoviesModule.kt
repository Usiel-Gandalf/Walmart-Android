package com.usi.exam.di.network

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
class NetworkMoviesModule {
    @Provides
    @Singleton
    @Named("MoviesInterceptor")
    fun providesOkHttpClientMovies(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    @Named("movieRetrofit")
    fun provideMovieRetrofit(@Named("MoviesInterceptor") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.URL_BASE_MOVIES)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}