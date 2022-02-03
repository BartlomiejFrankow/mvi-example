package com.example.mviexample

interface LoginRepository {

    fun login(email: String, password: String): Boolean
}
