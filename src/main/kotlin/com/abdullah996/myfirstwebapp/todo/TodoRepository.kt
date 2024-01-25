package com.abdullah996.myfirstwebapp.todo

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo,Int> {
    fun findByUserName(userName:String):List<Todo>
}