package com.usi.exam.ui.security.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usi.exam.di.interceptor.SimpleTokenProvider
import com.usi.exam.domain.repository.AuthRepository
import com.usi.exam.domain.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenProvider: SimpleTokenProvider
    ) : ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _firstname = mutableStateOf("")
    val firstname: State<String> get() = _firstname

    private val _lastname = mutableStateOf("")
    val lastname: State<String> get() = _lastname

    private val _country = mutableStateOf("")
    val country: State<String> get() = _country

    private val _registerStatusFinal = mutableStateOf(false)
    val registerStatusFinal: State<Boolean> get() = _registerStatusFinal

    var registerStatus = false
        private set

    fun registerUser(
        username: String,
        password: String,
        firstname: String,
        lastname: String,
        country: String,
    ) {
        viewModelScope.launch {
            val user = RegisterRequest(
                username = username,
                password = password,
                firstname = firstname,
                lastname = lastname,
                country = country
            )
            val response = authRepository.register(user = user)
            if (response.estatus){
                println("El token rey " + response.token)
                tokenProvider.setToken(response.token.toString())
                _registerStatusFinal.value = true
            }else{
                println(response.message)
                _registerStatusFinal.value = false
            }
        }
    }

    fun updateRegisterStatus(registerStatus: Boolean) {
        _registerStatusFinal.value = registerStatus
    }

    fun updateEmailText(text: CharSequence?) {
        _email.value = text.toString()
    }

    fun updatePasswordText(text: CharSequence?) {
        _password.value = text.toString()
    }

    fun updateFirstameText(text: CharSequence?) {
        _firstname.value = text.toString()
    }

    fun updateLastnameText(text: CharSequence?) {
        _lastname.value = text.toString()
    }

    fun updateCountryText(text: CharSequence?) {
        _country.value = text.toString()
    }
}