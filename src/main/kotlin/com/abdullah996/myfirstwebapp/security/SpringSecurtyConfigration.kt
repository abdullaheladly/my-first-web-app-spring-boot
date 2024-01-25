package com.abdullah996.myfirstwebapp.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SpringSecurityConfiguration {


    @Bean
    fun createUserDetailsManager(): InMemoryUserDetailsManager {

        val userDetails1= createNewUser("abdullah", "pass")
        val userDetails2= createNewUser("ahmed", "pass1")

        return InMemoryUserDetailsManager(userDetails1,userDetails2)
    }

    fun createNewUser(userName:String,password:String):UserDetails{
        val passwordEncoder: (String) -> String = { input -> passwordEncoder().encode(input) }
        return User.builder()
            .passwordEncoder(passwordEncoder)
            .username(userName)
            .password(password)
            .roles("USER", "ADMIN")
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity):SecurityFilterChain{
        httpSecurity.authorizeHttpRequests{auth->
            auth.anyRequest().authenticated()
        }
        httpSecurity.formLogin(Customizer.withDefaults())
        httpSecurity.csrf().disable()
        httpSecurity.headers().frameOptions().disable()
        return httpSecurity.build()
    }
}




