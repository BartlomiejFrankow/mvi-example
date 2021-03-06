package com.example.mviexample.ui.login

import com.example.mviexample.redux.State

/**
 * An implementation of [State] that describes the configuration of the login screen at a given time.
 */
data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showLoader: Boolean = false,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val goToDetailsScreen: Boolean = false
) : State
