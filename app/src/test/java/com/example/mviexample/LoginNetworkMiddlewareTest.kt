package com.example.mviexample

import com.example.mviexample.redux.Store
import com.example.mviexample.ui.FakeLoginRepository
import com.example.mviexample.ui.login.LoginAction
import com.example.mviexample.ui.login.LoginAction.*
import com.example.mviexample.ui.login.LoginReducer
import com.example.mviexample.ui.login.LoginViewState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginNetworkMiddlewareTest {

    @Test
    fun submitInvalidEmail() {
        // Given
        val fakeLoginRepository = FakeLoginRepository()

        val inputState = LoginViewState(email = "")

        val middlewareUnderTest = LoginNetworkMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(inputState, LoginReducer(), listOf(actionCaptureMiddleware))

        // When
        runBlocking {
            middlewareUnderTest.process(SignInButtonClicked, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(InvalidEmailSubmitted)
    }

    @Test
    fun submitInvalidPassword() {
        // Given
        val fakeLoginRepository = FakeLoginRepository()

        val inputState = LoginViewState(email = "test@email.com", password = "")

        val middlewareUnderTest = LoginNetworkMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(inputState, LoginReducer(), listOf(actionCaptureMiddleware))

        // When
        runBlocking {
            middlewareUnderTest.process(SignInButtonClicked, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(InvalidPasswordSubmitted)
    }

    @Test
    fun loginSuccess() {
        // Given
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginViewState(email = "test@email.com", password = "Password1")

        val middlewareUnderTest = LoginNetworkMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(inputState, LoginReducer(), listOf(actionCaptureMiddleware))

        // When
        runBlocking {
            middlewareUnderTest.process(SignInButtonClicked, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(LoginStarted)
        actionCaptureMiddleware.assertActionProcessed(LoginCompleted)
    }

    @Test
    fun loginFailure() {
        // Given
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = false

        val inputState = LoginViewState(email = "test@email.com", password = "Password1")

        val middlewareUnderTest = LoginNetworkMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(inputState, LoginReducer(), listOf(actionCaptureMiddleware))

        // When
        runBlocking {
            middlewareUnderTest.process(SignInButtonClicked, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(LoginStarted)
        actionCaptureMiddleware.assertActionProcessed(LoginFailed(null))
    }
}
