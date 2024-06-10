package com.usi.exam.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usi.exam.domain.models.MovieDetail
import com.usi.exam.domain.repository.MoviesRepository
import com.usi.exam.utils.ResponseAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiRepository: MoviesRepository,
) : ViewModel() {

    private val _individualMovieIdSelectedForDetail = mutableStateOf(0)

    var movieDetail: MovieDetail = MovieDetail()
        private set

    fun setComicIdForDetail(idMovie: Int) {
        _individualMovieIdSelectedForDetail.value = idMovie
        getDetailMovie()
    }

    private fun getDetailMovie() {
        viewModelScope.launch {
            when (val result =
                apiRepository.getDetailMovie(_individualMovieIdSelectedForDetail.value)) {
                is ResponseAPI.OnFailure -> {

                }

                is ResponseAPI.Success -> {
                    withContext(Dispatchers.Main) {
                        movieDetail = result.data
                    }
                }
            }

        }

    }
}