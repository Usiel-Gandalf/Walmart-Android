package com.usi.exam.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usi.exam.domain.models.Movie
import com.usi.exam.domain.repository.MoviesRepository
import com.usi.exam.utils.ResponseAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: MoviesRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    var filterMovieList: MutableList<Movie> = mutableStateListOf()
        private set

    init {
        getMoviesList()
    }

    fun getMoviesList() {
        _loading.value = true
        _error.value = false

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = apiRepository.getMovies()) {
                is ResponseAPI.OnFailure -> {
                    println("Ocurrió un problema")
                    _error.postValue(true)
                }

                is ResponseAPI.Success -> {
                    println("Todo salió bien")
                    _movieList.postValue(result.data)
                    _loading.postValue(false)
                }
            }
        }
    }
}