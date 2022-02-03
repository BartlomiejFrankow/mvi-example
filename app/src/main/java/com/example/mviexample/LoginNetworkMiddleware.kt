package com.example.mviexample

import com.example.mviexample.redux.Middleware
import com.example.mviexample.redux.Store
import com.example.mviexample.ui.login.LoginAction
import com.example.mviexample.ui.login.LoginAction.*
import com.example.mviexample.ui.login.LoginViewState

class LoginNetworkMiddleware(
    private val loginRepository: LoginRepository
) : Middleware<LoginViewState, LoginAction> {

    override suspend fun process(action: LoginAction, currentState: LoginViewState, store: Store<LoginViewState, LoginAction>) {
        when (action) {
            is SignInButtonClicked -> {
                store.dispatch(LoginStarted)

                val isSuccessful = loginRepository.login(
                    email = currentState.email,
                    password = currentState.password
                )

                if (isSuccessful) {
                    store.dispatch(LoginCompleted)
                } else {
                    store.dispatch(LoginFailed(null))
                }
            }
        }
    }
}