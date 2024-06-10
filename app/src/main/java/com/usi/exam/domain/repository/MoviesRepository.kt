package com.usi.exam.domain.repository

import com.usi.exam.domain.mappers.toDomain
import com.usi.exam.domain.models.Movie
import com.usi.exam.domain.models.MovieDetail
import com.usi.exam.domain.service.MovieService
import com.usi.exam.utils.ResponseAPI
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val movieService: MovieService) {
    suspend fun getMovies(): ResponseAPI<List<Movie>>{
            return try {
                val result = movieService.getListMovies()
                result.body()?.results?.get(0)?.toDomain()

                ResponseAPI.Success(result.body()?.results?.map {
                    it.toDomain()
                } ?: emptyList())
            } catch (e: Exception) {
                ResponseAPI.OnFailure(e.message.toString())
            }

    }

    suspend fun getDetailMovie(movieId: Int): ResponseAPI<MovieDetail>{
        return try {
            val result = movieService.getDetailMovie(movieId)
            ResponseAPI.Success(result.body()!!.toDomain())
        } catch (e: Exception) {
            ResponseAPI.OnFailure(e.message.toString())
        }
    }
}