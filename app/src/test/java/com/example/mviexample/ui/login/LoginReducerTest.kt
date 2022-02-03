package com.example.mviexample.ui.login

import com.example.mviexample.R
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginReducerTest {

    @Test
    fun onEmailChangedUpdateState() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.EmailChanged("test@email.com")

        val expectedState = inputState.copy(
            email = "test@email.com"
        )

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertEquals(expectedState, newState)
    }

    @Test
    fun onLoginStartedShowLoader() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginStarted

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertEquals(true, newState.showLoader)
    }

    @Test
    fun onLoginFailedHideLoader() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginFailed(null)

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertEquals(false, newState.showLoader)
    }

    @Test
    fun onInvalidEmailShowErrorMessage() {
        val inputState = LoginViewState()
        val reducer = LoginReducer()

        val newState = reducer.reduce(inputState, LoginAction.InvalidEmailSubmitted)

        assertEquals(R.string.email_error, newState.emailError)
    }

    @Test
    fun onValidAfterInvalidEmailHideErrorMessage() {
        val inputState = LoginViewState()
        val reducer = LoginReducer()

        // Invalid email action
        reducer.reduce(inputState, LoginAction.InvalidEmailSubmitted)

        // Valid email action
        val newState = reducer.reduce(inputState, LoginAction.ValidEmailSubmitted)

        assertEquals(null, newState.emailError)
    }

    @Test
    fun onInvalidPasswordShowErrorMessage() {
        val inputState = LoginViewState()

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, LoginAction.InvalidPasswordSubmitted)

        assertEquals(R.string.password_error, newState.passwordError)
    }

    @Test
    fun onValidAfterInvalidPasswordHideErrorMessage() {
        val inputState = LoginViewState()
        val reducer = LoginReducer()

        // Invalid email action
        reducer.reduce(inputState, LoginAction.InvalidPasswordSubmitted)

        // Valid email action
        val newState = reducer.reduce(inputState, LoginAction.ValidPasswordSubmitted)

        assertEquals(null, newState.passwordError)
    }
}
