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
                if (validateEmail(currentState, store)) return
                if (validatePassword(currentState, store)) return

                loginUser(store, currentState)
            }
        }
    }

    private suspend fun validatePassword(currentState: LoginViewState, store: Store<LoginViewState, LoginAction>) =
        if (currentState.password.isEmpty()) {
            store.dispatch(InvalidPasswordSubmitted)
            true
        } else {
            store.dispatch(ValidPasswordSubmitted)
            false
        }

    private suspend fun validateEmail(currentState: LoginViewState, store: Store<LoginViewState, LoginAction>) =
        if (currentState.email.isEmpty()) {
            store.dispatch(InvalidEmailSubmitted)
            true
        } else {
            store.dispatch(ValidEmailSubmitted)
            false
        }

    private suspend fun loginUser(
        store: Store<LoginViewState, LoginAction>,
        currentState: LoginViewState
    ) {
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