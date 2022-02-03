package com.example.mviexample

class LoginRepositoryImpl: LoginRepository {

    override fun login(email: String, password: String): Boolean {
        return true
    }
}
