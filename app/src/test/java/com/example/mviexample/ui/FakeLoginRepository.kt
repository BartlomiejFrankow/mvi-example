package com.example.mviexample.ui

import com.example.mviexample.LoginRepository

class FakeLoginRepository : LoginRepository {

    var shouldMockSuccess: Boolean = true

    override suspend fun login(email: String, password: String): Boolean {
        return shouldMockSuccess
    }
}
