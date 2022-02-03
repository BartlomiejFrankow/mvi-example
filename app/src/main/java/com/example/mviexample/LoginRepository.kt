package com.example.mviexample

interface LoginRepository {

    suspend fun login(email: String, password: String): Boolean
}
