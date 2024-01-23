package com.abdullah996.myfirstwebapp.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class SpringSecurityConfiguration {


    @Bean
    fun createUserDetailsManager(): InMemoryUserDetailsManager {
        val passwordEncoder: (String) -> String = { input -> passwordEncoder().encode(input) }

        val userDetails = User.builder()
            .passwordEncoder(passwordEncoder)
            .username("abdullah")
            .password("pass")
            .roles("USER", "ADMIN")
            .build()

        return InMemoryUserDetailsManager(userDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}




