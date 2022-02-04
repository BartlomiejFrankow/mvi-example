package com.example.mviexample.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviexample.LoggingMiddleware
import com.example.mviexample.LoginNetworkMiddleware
import com.example.mviexample.LoginRepositoryImpl
import com.example.mviexample.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            LoginNetworkMiddleware(LoginRepositoryImpl())
        )
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(newEmail: String) {
        viewModelScope.launch {
            store.dispatch(LoginAction.EmailChanged(newEmail))
        }
    }

    fun passwordChanged(newPassword: String) {
        viewModelScope.launch {
            store.dispatch(LoginAction.PasswordChanged(newPassword))
        }
    }

    fun signInButtonClicked() {
        viewModelScope.launch {
            store.dispatch(LoginAction.SignInButtonClicked)
        }
    }
}
