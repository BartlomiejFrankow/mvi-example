package com.example.mviexample.ui.login

import com.example.mviexample.R
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginReducerTest {

    @Test
    fun onEmailChangedUpdateState() {
        // Given
        val inputState = LoginViewState()
        val inputAction = LoginAction.EmailChanged("test@email.com")

        val expectedState = inputState.copy(
            email = "test@email.com"
        )

        // When
        val newState = LoginReducer().reduce(inputState, inputAction)

        // Then
        assertEquals(expectedState, newState)
    }

    @Test
    fun onLoginStartedShowLoader() {
        // Given
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginStarted

        // When
        val newState = LoginReducer().reduce(inputState, inputAction)

        // Then
        assertEquals(true, newState.showLoader)
    }

    @Test
    fun onLoginFailedHideLoader() {
        // Given
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginFailed(null)

        // When
        val newState = LoginReducer().reduce(inputState, inputAction)

        // Then
        assertEquals(false, newState.showLoader)
    }

    @Test
    fun onInvalidEmailShowErrorMessage() {
        // Given
        val inputState = LoginViewState()

        // When
        val newState = LoginReducer().reduce(inputState, LoginAction.InvalidEmailSubmitted)

        // Then
        assertEquals(R.string.email_error, newState.emailError)
    }

    @Test
    fun onValidAfterInvalidEmailHideErrorMessage() {
        // Given
        val inputState = LoginViewState()
        val reducer = LoginReducer()

        // When
        reducer.reduce(inputState, LoginAction.InvalidEmailSubmitted) // Invalid email action
        val newState = reducer.reduce(inputState, LoginAction.ValidEmailSubmitted) // Valid email action

        // Then
        assertEquals(null, newState.emailError)
    }

    @Test
    fun onInvalidPasswordShowErrorMessage() {
        // Given
        val inputState = LoginViewState()

        // When
        val newState = LoginReducer().reduce(inputState, LoginAction.InvalidPasswordSubmitted)

        // Then
        assertEquals(R.string.password_error, newState.passwordError)
    }

    @Test
    fun onValidAfterInvalidPasswordHideErrorMessage() {
        // Given
        val inputState = LoginViewState()
        val reducer = LoginReducer()

        // When
        reducer.reduce(inputState, LoginAction.InvalidPasswordSubmitted) // Invalid password action
        val newState = reducer.reduce(inputState, LoginAction.ValidPasswordSubmitted) // Valid password action

        // Then
        assertEquals(null, newState.passwordError)
    }
}
