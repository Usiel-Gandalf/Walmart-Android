package com.usi.exam.ui.security.login

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usi.exam.di.interceptor.SimpleTokenProvider
import com.usi.exam.domain.repository.AuthRepository
import com.usi.exam.domain.request.LoginRequest
import com.usi.exam.domain.usecase.BiometricAuthenticator
import com.usi.exam.utils.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val biometricAuthenticator: BiometricAuthenticator,
    private val authRepository: AuthRepository,
    private val tokenProvider: SimpleTokenProvider
): ViewModel(){

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _biometricEnabled = MutableStateFlow(false)
    val biometricEnabled: StateFlow<Boolean> get() = _biometricEnabled

    private val _authenticationState = MutableStateFlow<AuthenticationState>(AuthenticationState.Idle)
    val authenticationState: StateFlow<AuthenticationState> get() = _authenticationState


    fun loginUser(username: String, password: String){
        viewModelScope.launch {
            val credentials = LoginRequest(
                username = username,
                password = password
            )

            val response = authRepository.login(credentials = credentials)
            if (response.estatus){
                tokenProvider.setToken(response.token.toString())
                _isLoggedIn.value = true
            }else{
                println(response.message)

            }
        }
    }

    fun checkIfPhoneItsAbleToBiometricsAuthenticator() {
        _biometricEnabled.value = biometricAuthenticator.isBiometricAvailable()
    }

    fun authenticate(activity: AppCompatActivity) {
        biometricAuthenticator.authenticate(
            activity,
            onSuccess = { _authenticationState.value = AuthenticationState.Success },
            onFailure = { _authenticationState.value = AuthenticationState.Failure },
            onError = { message -> _authenticationState.value = AuthenticationState.Error(message) }
        )
    }


    fun updateEmailText(text: CharSequence?) {
        _email.value = text.toString()
    }

    fun updatePasswordText(text: CharSequence?) {
        _password.value = text.toString()
    }
}