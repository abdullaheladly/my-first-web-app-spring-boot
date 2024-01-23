package com.abdullah996.myfirstwebapp.login

import org.springframework.stereotype.Service


@Service
class AuthenticationService {
    fun authenticate(name: String, password: String): Boolean {
        return name.equals("abdullah", ignoreCase = true) &&
            password.equals("pass", ignoreCase = true)
    }
}