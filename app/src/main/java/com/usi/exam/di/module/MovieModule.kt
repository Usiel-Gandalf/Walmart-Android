package com.usi.exam.di.module

import com.usi.exam.domain.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    @Singleton
    fun provideMovieService(@Named("movieRetrofit") retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}