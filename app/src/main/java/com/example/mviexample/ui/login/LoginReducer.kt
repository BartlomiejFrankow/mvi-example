package com.example.mviexample.ui.login

import com.example.mviexample.R
import com.example.mviexample.redux.Reducer
import com.example.mviexample.ui.login.LoginAction.*

/**
 * This reducer is responsible for handling any [LoginAction], and using that to create a new [LoginViewState]
 */
class LoginReducer : Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        return when (action) {
            is EmailChanged -> currentState.copy(email = action.newEmail)
            is PasswordChanged -> currentState.copy(password = action.newPassword)
            is LoginFailed -> currentState.copy(showLoader = false)
            LoginCompleted -> currentState.copy(showLoader = false, goToDetailsScreen = true)
            LoginStarted -> currentState.copy(showLoader = true)
            SignInButtonClicked -> currentState
            InvalidEmailSubmitted -> currentState.copy(emailError = R.string.email_error)
            InvalidPasswordSubmitted -> currentState.copy(passwordError = R.string.password_error)
            ValidEmailSubmitted -> currentState.copy(emailError = null)
            ValidPasswordSubmitted -> currentState.copy(passwordError = null)
        }
    }
}
